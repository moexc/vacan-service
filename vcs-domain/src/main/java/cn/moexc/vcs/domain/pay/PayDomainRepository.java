package cn.moexc.vcs.domain.pay;

public interface PayDomainRepository {

    PayDomain byId(String orderId, String mode, String payTimeout);

    void save(PayDomain domain);
}
