package cn.moexc.vcs.web;

import cn.moexc.vcs.service.OrderService;
import cn.moexc.vcs.service.QueryOrderService;
import cn.moexc.vcs.service.dto.CreateOrderDTO;
import cn.moexc.vcs.service.dto.SearchOrderDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final QueryOrderService queryOrderService;

    public OrderController(OrderService orderService,
                           QueryOrderService queryOrderService) {
        this.orderService = orderService;
        this.queryOrderService = queryOrderService;
    }

    @PostMapping("/search")
    public R select(@Auth User user,
                    @RequestBody SearchOrderDTO searchOrderDTO,
                    @RequestParam("page") Integer page,
                    @RequestParam("rows") Integer rows){
        return R.success(queryOrderService.selectList(user.getUserId(), searchOrderDTO, page, rows));
    }

    @PostMapping
    public R create(@RequestBody @Valid CreateOrderDTO createOrderDTO, @Auth User user){
        String orderId = orderService.create(createOrderDTO, user.getUserId());
        return R.success(orderId);
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") String orderId){
        return R.success(queryOrderService.detail(orderId));
    }

    @PutMapping("/{id}/cancel")
    public R cancel(@PathVariable("id") String orderId, @Auth User user){
        orderService.cancelOne(orderId, user.getUserId());
        return R.success();
    }

    @PutMapping("/{id}/deliver")
    public R deliver(@PathVariable("id") String orderId, @Auth User user){
        orderService.deliver(orderId);
        return R.success();
    }

    @PutMapping("/{id}/accept")
    public R accept(@PathVariable("id") String orderId, @Auth User user){
        orderService.accept(orderId);
        return R.success();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String orderId, @Auth User user){
        orderService.deleteOne(orderId, user.getUserId());
        return R.success();
    }

}
