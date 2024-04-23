package cn.moexc.vcs.infrasture.pay;

import cn.moexc.vcs.domain.pay.PayDomain;
import cn.moexc.vcs.domain.pay.PayDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import cn.moexc.vcs.infrasture.jpa.entity.PayInfoEntity;
import cn.moexc.vcs.infrasture.jpa.entity.PayInfoEntityKey;
import cn.moexc.vcs.infrasture.jpa.repository.IndentEntityRepository;
import cn.moexc.vcs.infrasture.jpa.repository.PayInfoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PayDomainRepositoryImpl implements PayDomainRepository {

    private final IndentEntityRepository indentEntityRepository;
    private final PayInfoEntityRepository payInfoEntityRepository;

    @Autowired
    public PayDomainRepositoryImpl(IndentEntityRepository indentEntityRepository, PayInfoEntityRepository payInfoEntityRepository) {
        this.indentEntityRepository = indentEntityRepository;
        this.payInfoEntityRepository = payInfoEntityRepository;
    }

    @Override
    public PayDomain byId(String orderId, String mode, String payTimeout) {
        Optional<IndentEntity> indentEntityOptional = indentEntityRepository.findById(orderId);
        IndentEntity indentEntity = indentEntityOptional.orElseThrow(() -> new RuntimeException("获取订单信息失败"));
        Optional<PayInfoEntity> payInfoEntityOptional = payInfoEntityRepository.findById(new PayInfoEntityKey(orderId, mode));
        PayInfoEntity payInfoEntity = payInfoEntityOptional.orElse(null);
        return PayDomainFactory.genDomain(indentEntity, payInfoEntity, payTimeout);
    }

    @Override
    public void save(PayDomain domain) {
        payInfoEntityRepository.save(PayDomainFactory.genEntity(domain));
    }
}
