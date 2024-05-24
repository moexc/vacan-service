package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.entity.GoodClassify;
import cn.moexc.vcs.infrasture.mybatis.mapper.GoodClassifyMapper;
import cn.moexc.vcs.service.vo.GoodsTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryGoodsTypeService {

    private static final String ROOT_CODE = "0000";

    private final GoodClassifyMapper goodClassifyMapper;

    public QueryGoodsTypeService(GoodClassifyMapper goodClassifyMapper) {
        this.goodClassifyMapper = goodClassifyMapper;
    }


    public List<GoodsTypeVO> rootTypes(){
        List<GoodClassify> source = goodClassifyMapper.findAllByParentCodeOrderBySort(ROOT_CODE);
        return source.stream().map(GoodsTypeVO::gen).collect(Collectors.toList());
    }

}
