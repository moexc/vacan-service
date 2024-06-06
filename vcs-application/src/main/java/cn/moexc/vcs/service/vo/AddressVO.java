package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.mybatis.entity.AddressDelivery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressVO {
    private String id;
    private String address;
    private String postCode;
    private String isdefault;

    public static AddressVO gen(AddressDelivery entity){
        AddressVO addressVO = new AddressVO();
        addressVO.setId(entity.getId());
        addressVO.setAddress(entity.getCity().replaceAll(",", "") + entity.getDetailed());
        addressVO.setPostCode(entity.getPostCode());
        addressVO.setIsdefault(entity.getIsdefault());
        return addressVO;
    }

}
