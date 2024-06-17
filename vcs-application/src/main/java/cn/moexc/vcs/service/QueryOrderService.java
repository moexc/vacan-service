package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.mapper.IndentMapper;
import cn.moexc.vcs.infrasture.queryresult.OrderVO;
import cn.moexc.vcs.service.dto.SearchOrderDTO;
import cn.moexc.vcs.service.vo.PageResult;
import cn.moexc.vcs.service.vo.PageResultFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryOrderService {

    private final IndentMapper indentMapper;

    public QueryOrderService(IndentMapper indentMapper) {
        this.indentMapper = indentMapper;
    }

    public PageResult<OrderVO> selectList(String cid, SearchOrderDTO searchOrderDTO, Integer page, Integer rows){
        PageHelper.startPage(page, rows);
        List<OrderVO> list = indentMapper.selectList(cid, searchOrderDTO.getTitle(), searchOrderDTO.getStatus(),
                searchOrderDTO.getCreateTimeRangeBefore(), searchOrderDTO.getCreateTimeRangeAfter());
        return new PageResultFactory<>(list).of();
    }

    public OrderVO detail(String orderId){
        return indentMapper.selectDetail(orderId);
    }
}
