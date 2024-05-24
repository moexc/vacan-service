package cn.moexc.vcs.infrasture.mybatis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.moexc.vcs.infrasture.mybatis.entity.GoodClassify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodClassifyMapper {
    int deleteByPrimaryKey(String code);

    int insert(GoodClassify record);

    GoodClassify selectByPrimaryKey(String code);

    int updateByPrimaryKey(GoodClassify record);

    List<GoodClassify> findAllByParentCodeOrderBySort(@Param("parentCode")String parentCode);


}