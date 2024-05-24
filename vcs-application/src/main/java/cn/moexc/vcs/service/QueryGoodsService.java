package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.mapper.GoodsMapper;
import cn.moexc.vcs.infrasture.queryresult.GoodsSimpleVO;
import cn.moexc.vcs.infrasture.queryresult.GoodsDetailVO;
import cn.moexc.vcs.service.dto.SearchGoodsDTO;
import cn.moexc.vcs.service.vo.PageResult;
import cn.moexc.vcs.service.vo.PageResultFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryGoodsService {

    private final GoodsMapper goodsMapper;

    public QueryGoodsService(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public List<GoodsSimpleVO> todayStar(){
        return goodsMapper.todayStar();
    }

    public PageResult<GoodsSimpleVO> select(SearchGoodsDTO searchGoodsDTO, Integer page, Integer rows){
        PageHelper.startPage(page, rows);
        List<GoodsSimpleVO> list = goodsMapper.findList(searchGoodsDTO.getName(), searchGoodsDTO.getTimeRangeBefore(), searchGoodsDTO.getTimeRangeAfter(), searchGoodsDTO.getStatus());
        return new PageResultFactory<>(list).of();
    }

    public GoodsDetailVO selectDetailById(String id){
        return goodsMapper.selectGoodsDetail(id);
    }
}
