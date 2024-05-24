package cn.moexc.vcs.infrasture.mybatis.mapper;

import cn.moexc.vcs.infrasture.mybatis.entity.Goods;
import cn.moexc.vcs.infrasture.queryresult.GoodsDetailVO;
import cn.moexc.vcs.infrasture.queryresult.GoodsSimpleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKey(Goods record);

    List<GoodsSimpleVO> todayStar();

    List<GoodsSimpleVO> findList(@Param("name") String name,
                                 @Param("timeRangeBefore") String timeRangeBefore,
                                 @Param("timeRangeAfter") String timeRangeAfter,
                                 @Param("status") String status);

    GoodsDetailVO selectGoodsDetail(@Param("id") String id);
}