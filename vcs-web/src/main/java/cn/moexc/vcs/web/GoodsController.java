package cn.moexc.vcs.web;

import cn.moexc.vcs.service.GoodsService;
import cn.moexc.vcs.service.QueryGoodsService;
import cn.moexc.vcs.service.dto.GoodsDTO;
import cn.moexc.vcs.service.dto.SearchGoodsDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final QueryGoodsService queryGoodsService;

    public GoodsController(GoodsService goodsService, QueryGoodsService queryGoodsService) {
        this.goodsService = goodsService;
        this.queryGoodsService = queryGoodsService;
    }

    @PostMapping("/search")
    public R findGoods(@RequestBody SearchGoodsDTO searchGoodsDTO,
                       @RequestParam("page") Integer page,
                       @RequestParam("rows") Integer rows,
                       @Auth User user){
        return R.success(queryGoodsService.select(searchGoodsDTO, page, rows));
    }

    @PostMapping
    public R create(@RequestBody GoodsDTO dto){
        goodsService.saveGoods(dto);
        return R.success();
    }

    @PutMapping("/{id}")
    public R update(@RequestBody GoodsDTO dto, @PathVariable("id") String id){
        goodsService.updateGoods(dto, id);
        return R.success();
    }

    @PatchMapping("/{id}")
    public R patch(@RequestParam("status") String status, @PathVariable String id){
        goodsService.updateStatus(status, id);
        return R.success();
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable("id") String id){
        return R.success(queryGoodsService.selectDetailById(id));
    }

    @GetMapping("/today-star")
    public R todayStar(){
        return R.success(queryGoodsService.todayStar());
    }
}
