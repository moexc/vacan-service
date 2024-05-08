package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateGoodsDTO {
    private String title;
    private MultipartFile photo;
    private String subdescr;
    private String detail;
    private BigDecimal oldPrice;
    private BigDecimal price;
    private Integer quantity;
    private String classify;
}
