package cn.moexc.vcs.web;

import cn.moexc.vcs.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pay")
public class PayController {

    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/{orderId}")
    public R qrCode(@PathVariable String orderId, @RequestParam("mode") String mode){
        return R.success(payService.pay(orderId, mode));
    }

    @PostMapping("/async")
    public void payAsync(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration<String> names = request.getParameterNames();
        Map<String,String> notifyMap = new HashMap<>();
        String name;
        while (names.hasMoreElements()){
            name = names.nextElement();
            notifyMap.put(name, request.getParameter(name));
        }
        payService.payAsync(notifyMap, response);
    }

    @GetMapping("/result/{orderId}")
    public R result(@PathVariable("orderId") String orderId){
        return R.success(payService.payResult(orderId));
    }

}
