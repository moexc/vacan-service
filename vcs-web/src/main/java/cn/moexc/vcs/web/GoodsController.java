package cn.moexc.vcs.web;

import cn.moexc.vcs.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/today-star")
    public R todayStar(){
        return R.success(goodsService.todayStar());
    }

    @GetMapping
    public R findGoods(){
        return R.success(goodsService.select());
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable("id") String id){
        return R.success(goodsService.selectById(id));
    }
}
