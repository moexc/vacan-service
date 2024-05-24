package cn.moexc.vcs.web;

import cn.moexc.vcs.service.QueryGoodsTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods-type")
public class GoodsTypeController {

    private final QueryGoodsTypeService queryGoodsTypeService;

    public GoodsTypeController(QueryGoodsTypeService queryGoodsTypeService) {
        this.queryGoodsTypeService = queryGoodsTypeService;
    }

    @GetMapping
    public R list(){
        return R.success(queryGoodsTypeService.rootTypes());
    }

}
