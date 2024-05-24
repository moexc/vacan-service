package cn.moexc.vcs.infrasture.mybatis.mapper;

import cn.moexc.vcs.infrasture.mybatis.entity.Trade;
import cn.moexc.vcs.infrasture.queryresult.TradeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Trade record);

    Trade selectByPrimaryKey(String id);

    int updateByPrimaryKey(Trade record);

    List<TradeVO> findList(@Param("tradeName") String tradeName,
                           @Param("timeRangeBefore") String timeRangeBefore,
                           @Param("timeRangeAfter") String timeRangeAfter,
                           @Param("tradeStatus") String tradeStatus,
                           @Param("sendStatus") String sendStatus);
    List<TradeVO> pused();
}