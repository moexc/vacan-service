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

    public OrderController(OrderService orderService, QueryOrderService queryOrderService) {
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

    @PutMapping("/{id}")
    public R put(@PathVariable("id") String orderId,
                 @RequestParam("operation") String operation,
                 @Auth User user){
        if (operation.equals("CancelOrder")){
            orderService.cancelOne(orderId, user.getUserId());
        }else if (operation.equals("Deliver")){
            orderService.deliver(orderId);
        }else if (operation.equals("Accept")){
            orderService.accept(orderId);
        }
        return R.success();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String orderId, @Auth User user){
        orderService.deleteOne(orderId, user.getUserId());
        return R.success();
    }

}
