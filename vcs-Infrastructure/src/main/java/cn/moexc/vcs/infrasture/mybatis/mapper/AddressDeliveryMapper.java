package cn.moexc.vcs.infrasture.mybatis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.moexc.vcs.infrasture.mybatis.entity.AddressDelivery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDeliveryMapper {
    int deleteByPrimaryKey(String id);

    int insert(AddressDelivery record);

    AddressDelivery selectByPrimaryKey(String id);

    int updateByPrimaryKey(AddressDelivery record);

    AddressDelivery findDefault();

    List<AddressDelivery> findByCustomerIdOrderByIsdefaultDescAndCity(@Param("customerId")String customerId);

}