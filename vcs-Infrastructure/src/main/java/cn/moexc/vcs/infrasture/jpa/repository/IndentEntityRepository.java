package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndentEntityRepository extends JpaRepository<IndentEntity, String>, JpaSpecificationExecutor<IndentEntity> {
    List<IndentEntity> findByStatusAndCreateTimeBefore(String status, Date expireTime);
}