package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.goods.CreateGoodsCommand;
import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.goods.GoodsDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsDetail;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple;
import cn.moexc.vcs.infrasture.jpa.repository.GoodsEntityRepository;
import cn.moexc.vcs.infrasture.oss.ObjectStorageService;
import cn.moexc.vcs.service.cmdfactory.CreateGoodsCmdFactory;
import cn.moexc.vcs.service.dto.CreateGoodsDTO;
import cn.moexc.vcs.service.vo.GoodsVO4Detail;
import cn.moexc.vcs.service.vo.GoodsVO4Simple;
import cn.moexc.vcs.service.vo.PageResult;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    private final GoodsEntityRepository goodsEntityRepository;
    private final GoodsDomainRepository goodsDomainRepository;
    private final ObjectStorageService objectStorageService;
    private final RedissonClient redissonClient;

    public GoodsService(GoodsEntityRepository goodsEntityRepository,
                        GoodsDomainRepository goodsDomainRepository,
                        ObjectStorageService objectStorageService,
                        RedissonClient redissonClient) {
        this.goodsEntityRepository = goodsEntityRepository;
        this.goodsDomainRepository = goodsDomainRepository;
        this.objectStorageService = objectStorageService;
        this.redissonClient = redissonClient;
    }


    public List<GoodsVO4Simple> todayStar(){
        List<GoodsSimple> source = goodsEntityRepository.todayStar();
        return source.stream().map(GoodsVO4Simple::gen).collect(Collectors.toList());
    }

    public PageResult<GoodsVO4Simple> select(Integer page, Integer rows){
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<GoodsSimple> source = goodsEntityRepository.selectGoods(pageable);

        PageResult<GoodsVO4Simple> result = new PageResult<>();
        result.setTotal(source.getTotalElements());
        result.setData(source.getContent().stream().map(GoodsVO4Simple::gen).collect(Collectors.toList()));

        return result;
    }

    public GoodsVO4Detail selectById(String id){
        GoodsDetail source = goodsEntityRepository.selectGoodsDetail(id);
        return GoodsVO4Detail.gen(source);
    }
    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(CreateGoodsDTO createGoodsDTO){
        MultipartFile photo = createGoodsDTO.getPhoto();
        String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
        String url;
        try (InputStream photoStream = photo.getInputStream()){
            objectStorageService.write(fileName, photoStream);
            url = objectStorageService.getUrl(fileName);
        }catch (Exception e){
            e.printStackTrace();
            throw new AlterException("保存图片失败");
        }

        CreateGoodsCommand cmd = CreateGoodsCmdFactory.gen(createGoodsDTO, url);
        GoodsDomain domain = new GoodsDomain();
        domain.create(cmd);
        RLock lock = redissonClient.getLock(domain.getId());
        if (!lock.tryLock()){
            throw new AlterException("获取锁失败");
        }
        try{
            goodsDomainRepository.save(domain);
        }finally {
            lock.unlock();
        }
    }
}
