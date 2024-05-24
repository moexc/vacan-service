package cn.moexc.vcs.infrasture.mybatis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.moexc.vcs.infrasture.mybatis.entity.TradeBid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeBidMapper {
    int deleteByPrimaryKey(String id);

    int insert(TradeBid record);

    TradeBid selectByPrimaryKey(String id);

    int updateByPrimaryKey(TradeBid record);

    List<TradeBid> selectAllByTradeIdOrderByNumber(@Param("tradeId")String tradeId);

    int deleteByTradeId(@Param("tradeId")String tradeId);

    int insertList(@Param("list")List<TradeBid> list);

}