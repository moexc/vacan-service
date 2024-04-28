package cn.moexc.vcs.web;

import cn.moexc.vcs.service.TradeService;
import cn.moexc.vcs.service.dto.TradeDTO;
import cn.moexc.vcs.service.dto.SearchTradeDTO;
import cn.moexc.vcs.service.dto.TradeResultDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/search")
    public R list(@RequestBody SearchTradeDTO searchTradeDTO,
                  @RequestParam("page") Integer page,
                  @RequestParam("rows") Integer rows,
                  @Auth User user){
        return R.success(tradeService.list(searchTradeDTO, page, rows));
    }

    @PostMapping
    public R create(@RequestBody TradeDTO tradeDTO, @Auth User user){
        return R.success(tradeService.create(tradeDTO));
    }

    @GetMapping("/{tradeId}")
    public R detail(@PathVariable("tradeId") String tradeId){
        return R.success(tradeService.detail(tradeId));
    }

    @PutMapping("/{tradeId}")
    public R update(@PathVariable("tradeId") String tradeId, @RequestBody TradeDTO tradeDTO, @Auth User user){
        tradeService.update(tradeId, tradeDTO);
        return R.success();
    }

    @DeleteMapping("/{tradeId}")
    public R delete(@PathVariable("tradeId") String tradeId, @Auth User user){
        tradeService.delete(tradeId);
        return R.success();
    }


//    @PutMapping("/{tradeId}")
//    public R tradeOper(@PathVariable("tradeId") String tradeId,
//                         @RequestParam("operation") String operation,
//                         @Auth User user){
//        if ("Send2Engine".equals(operation)){
//            return R.success(tradeService.send2Engine(tradeId));
//        }
//        return R.success();
//    }

    @GetMapping("/pushed")
    public R pushed(){
        return R.success(tradeService.pushed());
    }

    @PostMapping("/result")
    public R tradeResult(@RequestBody TradeResultDTO tradeResultDTO){
        tradeService.acceptResult(tradeResultDTO);
        return R.success();
    }

}
