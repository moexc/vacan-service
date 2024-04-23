package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.PayInfoEntity;
import cn.moexc.vcs.infrasture.jpa.entity.PayInfoEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PayInfoEntityRepository extends JpaRepository<PayInfoEntity, PayInfoEntityKey>, JpaSpecificationExecutor<PayInfoEntity> {

}