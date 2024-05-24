package cn.moexc.vcs.service;

import cn.moexc.vcs.infrasture.mybatis.entity.AddressDelivery;
import cn.moexc.vcs.infrasture.mybatis.mapper.AddressDeliveryMapper;
import cn.moexc.vcs.service.vo.AddressVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryAddressService {

    private final AddressDeliveryMapper addressDeliveryMapper;

    public QueryAddressService(AddressDeliveryMapper addressDeliveryMapper) {
        this.addressDeliveryMapper = addressDeliveryMapper;
    }

    public List<AddressVO> list(String cid) {
        List<AddressDelivery> addresses = addressDeliveryMapper.findByCustomerIdOrderByIsdefaultDescAndCity(cid);
        return addresses.stream().map(AddressVO::gen).collect(Collectors.toList());
    }
}
