package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.jpa.entity.AddressDeliveryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressVO {
    private String id;
    private String province;
    private String address;
    private String detailed;
    private String postCode;
    private String isdefault;

    public static AddressVO gen(AddressDeliveryEntity entity){
        AddressVO addressVO = new AddressVO();
        addressVO.setId(entity.getId());
        addressVO.setAddress(entity.getCity().replaceAll(",", "") + entity.getDetailed());
        addressVO.setPostCode(entity.getPostCode());
        addressVO.setIsdefault(entity.getIsdefault());
        return addressVO;
    }

}
