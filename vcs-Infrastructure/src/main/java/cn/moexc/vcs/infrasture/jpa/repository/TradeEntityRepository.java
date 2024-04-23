package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.TradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TradeEntityRepository extends JpaRepository<TradeEntity, String>, JpaSpecificationExecutor<TradeEntity> {
    List<TradeEntity> findAllBySendStatusAndStatusNotOrderByStartTimeAsc(String sendStatus, String status);
}