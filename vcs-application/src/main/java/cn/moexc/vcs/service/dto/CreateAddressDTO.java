package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateAddressDTO {

    /**
     * 省,市,区
     */
    @NotBlank(message = "参数不可为空：省,市,区")
    private String city;

    /**
     * 详细地址
     */
    @NotBlank(message = "参数不可为空：详细地址")
    private String detailed;

    /**
     * 邮编
     */
    @NotBlank(message = "参数不可为空：邮编")
    private String postCode;

    /**
     * 是否默认
     */
    @NotBlank(message = "参数不可为空：是否设为默认")
    private String isdefault;

}
