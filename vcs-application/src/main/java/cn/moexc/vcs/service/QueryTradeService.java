package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.entity.Trade;
import cn.moexc.vcs.infrasture.mybatis.entity.TradeBid;
import cn.moexc.vcs.infrasture.mybatis.mapper.TradeBidMapper;
import cn.moexc.vcs.infrasture.mybatis.mapper.TradeMapper;
import cn.moexc.vcs.infrasture.queryresult.TradeVO;
import cn.moexc.vcs.service.dto.SearchTradeDTO;
import cn.moexc.vcs.service.dto.TradeDTO;
import cn.moexc.vcs.service.vo.PageResult;
import cn.moexc.vcs.service.vo.PageResultFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryTradeService {

    private final TradeMapper tradeMapper;
    private final TradeBidMapper tradeBidMapper;

    public QueryTradeService(TradeMapper tradeMapper, TradeBidMapper tradeBidMapper) {
        this.tradeMapper = tradeMapper;
        this.tradeBidMapper = tradeBidMapper;
    }

    public PageResult<TradeVO> list(SearchTradeDTO searchTradeDTO, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TradeVO> list = tradeMapper.findList(searchTradeDTO.getTradeName(), searchTradeDTO.getTimeRangeBefore(), searchTradeDTO.getTimeRangeAfter(),
                searchTradeDTO.getTradeStatus(), searchTradeDTO.getSendStatus());
        return new PageResultFactory<>(list).of();
    }

    public TradeDTO detail(String tradeId){
        Trade trade = tradeMapper.selectByPrimaryKey(tradeId);
        List<TradeBid> bids = tradeBidMapper.selectAllByTradeIdOrderByNumber(tradeId);
        return TradeDTO.gen(trade, bids);
    }

    public List<TradeVO> pushed() {
        return tradeMapper.pused();
    }
}
