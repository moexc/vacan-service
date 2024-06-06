package cn.moexc.vcs.infrasture.mybatis.mapper;

import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import cn.moexc.vcs.infrasture.queryresult.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IndentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Indent record);

    Indent selectByPrimaryKey(String id);

    int updateByPrimaryKey(Indent record);

    List<OrderVO> selectList(@Param("cid") String cid,
                             @Param("title") String title,
                             @Param("status") String status,
                             @Param("createTimeRangeBefore") String createTimeRangeBefore,
                             @Param("createTimeRangeAfter") String createTimeRangeAfter,
                             @Param("payTimeRangeBefore") String payTimeRangeBefore,
                             @Param("payTimeRangeAfter") String ayTimeRangeAfter);

    OrderVO selectDetail(@Param("orderId") String orderId);
}