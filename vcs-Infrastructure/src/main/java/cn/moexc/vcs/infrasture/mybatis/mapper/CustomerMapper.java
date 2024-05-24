package cn.moexc.vcs.infrasture.mybatis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.moexc.vcs.infrasture.mybatis.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    Customer selectByPrimaryKey(String id);

    int updateByPrimaryKey(Customer record);

    Customer selectOneByAccountNum(@Param("accountNum")String accountNum);

    Customer selectOneByAccountNumAndAccountPwd(@Param("accountNum")String accountNum,@Param("accountPwd")String accountPwd);
}