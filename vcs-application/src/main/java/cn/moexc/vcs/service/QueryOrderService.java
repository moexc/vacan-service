package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.mapper.IndentMapper;
import cn.moexc.vcs.infrasture.queryresult.OrderVO;
import cn.moexc.vcs.service.dto.SearchOrderDTO;
import cn.moexc.vcs.service.vo.PageResult;
import cn.moexc.vcs.service.vo.PageResultFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryOrderService {

    private final IndentMapper indentMapper;

    public QueryOrderService(IndentMapper indentMapper) {
        this.indentMapper = indentMapper;
    }

    public PageResult<OrderVO> selectList(String cid, SearchOrderDTO searchOrderDTO, Integer page, Integer rows){
        List<OrderVO> list = indentMapper.selectList(cid, searchOrderDTO.getTitle(), searchOrderDTO.getStatus(),
                searchOrderDTO.getCreateTimeRangeBefore(), searchOrderDTO.getCreateTimeRangeAfter(),
                searchOrderDTO.getPayTimeRangeBefore(), searchOrderDTO.getPayTimeRangeAfter());
        return new PageResultFactory<>(list).of();
    }
}
