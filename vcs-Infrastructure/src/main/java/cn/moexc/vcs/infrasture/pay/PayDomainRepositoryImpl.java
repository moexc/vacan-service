package cn.moexc.vcs.infrasture.pay;

import cn.moexc.vcs.domain.pay.PayDomain;
import cn.moexc.vcs.domain.pay.PayDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import cn.moexc.vcs.infrasture.mybatis.entity.PayInfo;
import cn.moexc.vcs.infrasture.mybatis.mapper.IndentMapper;
import cn.moexc.vcs.infrasture.mybatis.mapper.PayInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PayDomainRepositoryImpl implements PayDomainRepository {

    private final IndentMapper indentMapper;
    private final PayInfoMapper payInfoMapper;

    public PayDomainRepositoryImpl(IndentMapper indentMapper, PayInfoMapper payInfoMapper) {
        this.indentMapper = indentMapper;
        this.payInfoMapper = payInfoMapper;
    }


    @Override
    public PayDomain byId(String orderId, String mode, String payTimeout) {
        Indent indent = indentMapper.selectByPrimaryKey(orderId);
        PayInfo payInfo = payInfoMapper.selectByPrimaryKey(orderId, mode);
        return PayDomainFactory.genDomain(indent, payInfo, payTimeout);
    }

    @Override
    public void save(PayDomain domain) {
        payInfoMapper.insert(PayDomainFactory.genEntity(domain));
    }
}
