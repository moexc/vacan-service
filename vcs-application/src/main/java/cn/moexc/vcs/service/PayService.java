package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.domain.pay.PayDomain;
import cn.moexc.vcs.domain.pay.PayDomainRepository;
import cn.moexc.vcs.infrasture.pay.alipay.AliPay;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class PayService {

    private final PayDomainRepository payDomainRepository;
    private final AliPay aliPay;
    private final String payTimeout;
    private final OrderDomainRepository orderDomainRepository;

    @Autowired
    public PayService(PayDomainRepository payDomainRepository,
                      AliPay aliPay,
                      @Value("${order.pay-timeout}") String payTimeout,
                      OrderDomainRepository orderDomainRepository) {
        this.payDomainRepository = payDomainRepository;
        this.aliPay = aliPay;
        this.payTimeout = payTimeout;
        this.orderDomainRepository = orderDomainRepository;
    }

    public String pay(String orderId, String mode){
        PayDomain payDomain = payDomainRepository.byId(orderId, mode, payTimeout);
        String content = payDomain.getContent();
        if (content == null || content.length() == 0){
            payDomain.setPayMode(mode);
            if ("alipay_qrcode".equals(mode)){
                content = aliPay.qr(payDomain);
                payDomain.setContent(content);
                payDomainRepository.save(payDomain);
            }
        }
        return Base64.getEncoder().encodeToString(QRCode.from(content).to(ImageType.PNG).stream().toByteArray());
    }

    public void payAsync(Map<String,String> requestParams, HttpServletResponse response) throws IOException {
        String mode = "alipay_qrcode";
        if ("alipay_qrcode".equals(mode)){
            if (!aliPay.signature(requestParams)){
                response.getWriter().write("未通过验签");
            }
            if ("TRADE_SUCCESS".equals(requestParams.get("trade_status"))){
                String orderId = requestParams.get("out_trade_no");
                OrderDomain orderDomain = orderDomainRepository.byId(orderId);
                orderDomain.payAfter();
                orderDomainRepository.save(orderDomain);
            }
            response.getWriter().write("success");
        }
    }

    public String payResult(String orderId){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        return orderDomain.payIsSuccess() ? "1" : "0";
    }

}
