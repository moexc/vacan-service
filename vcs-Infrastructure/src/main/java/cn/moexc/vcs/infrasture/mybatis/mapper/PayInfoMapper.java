package cn.moexc.vcs.infrasture.mybatis.mapper;

import cn.moexc.vcs.infrasture.mybatis.entity.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayInfoMapper {
    int deleteByPrimaryKey(@Param("orderId") String orderId, @Param("mode") String mode);

    int insert(PayInfo record);

    PayInfo selectByPrimaryKey(@Param("orderId") String orderId, @Param("mode") String mode);

    int updateByPrimaryKey(PayInfo record);
}