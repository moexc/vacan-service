package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.goods.CreateGoodsCommand;
import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.goods.GoodsDomainRepository;
import cn.moexc.vcs.domain.goods.UpdateGoodsCommand;
import cn.moexc.vcs.service.cmdfactory.GoodsCmdFactory;
import cn.moexc.vcs.service.config.LockerException;
import cn.moexc.vcs.service.dto.GoodsDTO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {

    private final GoodsDomainRepository goodsDomainRepository;
    private final RedissonClient redissonClient;

    public GoodsService(GoodsDomainRepository goodsDomainRepository,
                        RedissonClient redissonClient) {
        this.goodsDomainRepository = goodsDomainRepository;
        this.redissonClient = redissonClient;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(GoodsDTO goodsDTO){
        CreateGoodsCommand cmd = GoodsCmdFactory.createCmd(goodsDTO);
        GoodsDomain domain = new GoodsDomain();
        domain.create(cmd);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGoods(GoodsDTO dto, String id){
        GoodsDomain domain = goodsDomainRepository.byId(id);
        UpdateGoodsCommand cmd = GoodsCmdFactory.updateCmd(dto);
        domain.update(cmd);
        RLock lock = redissonClient.getLock(domain.getId());
        if (!lock.tryLock()) throw new LockerException();
        try{
            goodsDomainRepository.save(domain);
        }finally {
            lock.unlock();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String status, String id) {
        GoodsDomain domain = goodsDomainRepository.byId(id);
        if ("03".equals(status)) domain.up();
        else if ("04".equals(status)) domain.down();
        else if ("05".equals(status)) domain.delete();
        else throw new AlterException("未知操作");
        RLock lock = redissonClient.getLock(domain.getId());
        if (!lock.tryLock()) throw new LockerException();
        try{
            goodsDomainRepository.save(domain);
        }finally {
            lock.unlock();
        }
    }
}
