package cn.moexc.vcs.web;

import cn.moexc.vcs.service.GoodsTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/goods-type")
public class GoodsTypeController {

    private final GoodsTypeService goodsTypeService;

    public GoodsTypeController(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @GetMapping
    public R list(){
        return R.success(goodsTypeService.rootTypes());
    }

}
