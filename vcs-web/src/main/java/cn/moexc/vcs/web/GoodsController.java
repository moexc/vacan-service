package cn.moexc.vcs.web;

import cn.moexc.vcs.service.GoodsService;
import cn.moexc.vcs.service.dto.CreateGoodsDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("/search")
    public R findGoods(@RequestParam("page") Integer page,
                       @RequestParam("rows") Integer rows,
                       @Auth User user){
        return R.success(goodsService.select(page, rows));
    }

    @PostMapping
    public R create(CreateGoodsDTO dto){
        goodsService.saveGoods(dto);
        return R.success();
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable("id") String id){
        return R.success(goodsService.selectById(id));
    }

    @GetMapping("/today-star")
    public R todayStar(){
        return R.success(goodsService.todayStar());
    }
}
