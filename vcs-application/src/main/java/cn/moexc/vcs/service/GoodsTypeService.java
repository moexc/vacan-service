package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.jpa.entity.GoodClassifyEntity;
import cn.moexc.vcs.infrasture.jpa.repository.GoodClassifyEntityRepository;
import cn.moexc.vcs.service.vo.GoodsTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsTypeService {

    private static final String ROOT_CODE = "0000";

    private final GoodClassifyEntityRepository goodClassifyEntityRepository;

    public GoodsTypeService(GoodClassifyEntityRepository goodClassifyEntityRepository) {
        this.goodClassifyEntityRepository = goodClassifyEntityRepository;
    }

    public List<GoodsTypeVO> rootTypes(){
        List<GoodClassifyEntity> source = goodClassifyEntityRepository.findByParentCodeOrderBySort(ROOT_CODE);
        return source.stream().map(GoodsTypeVO::gen).collect(Collectors.toList());
    }

}
