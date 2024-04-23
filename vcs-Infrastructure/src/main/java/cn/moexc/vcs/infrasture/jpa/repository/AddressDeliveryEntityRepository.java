package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.AddressDeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AddressDeliveryEntityRepository extends JpaRepository<AddressDeliveryEntity, String>, JpaSpecificationExecutor<AddressDeliveryEntity> {
    List<AddressDeliveryEntity> findByCustomerIdOrderByIsdefaultDescCityAsc(String cid);
    AddressDeliveryEntity findByIsdefault(String id);
    int countByCustomerId(String cid);
}