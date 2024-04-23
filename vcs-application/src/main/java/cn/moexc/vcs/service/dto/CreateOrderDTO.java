package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateOrderDTO {
    @NotNull(message = "商品编号不可为空")
    private String shopId;
    @Min(value = 1,message = "购买数量需要大于等于1")
    private Integer quantity;
    @NotBlank(message = "收货地址不可为空")
    private String address;
}
