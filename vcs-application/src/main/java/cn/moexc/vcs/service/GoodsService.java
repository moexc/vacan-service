package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsDetail;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple;
import cn.moexc.vcs.infrasture.jpa.repository.GoodsEntityRepository;
import cn.moexc.vcs.service.vo.GoodsVO4Detail;
import cn.moexc.vcs.service.vo.GoodsVO4Simple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    private final GoodsEntityRepository goodsEntityRepository;

    public GoodsService(GoodsEntityRepository goodsEntityRepository) {
        this.goodsEntityRepository = goodsEntityRepository;
    }


    public List<GoodsVO4Simple> todayStar(){
        List<GoodsSimple> source = goodsEntityRepository.todayStar();
        return source.stream().map(GoodsVO4Simple::gen).collect(Collectors.toList());
    }

    public List<GoodsVO4Simple> select(){
        List<GoodsSimple> source = goodsEntityRepository.selectGoods();
        return source.stream().map(GoodsVO4Simple::gen).collect(Collectors.toList());
    }

    public GoodsVO4Detail selectById(String id){
        GoodsDetail source = goodsEntityRepository.selectGoodsDetail(id);
        return GoodsVO4Detail.gen(source);
    }
}
