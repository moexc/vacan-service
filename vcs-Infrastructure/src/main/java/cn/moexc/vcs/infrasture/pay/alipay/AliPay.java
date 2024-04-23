package cn.moexc.vcs.infrasture.pay.alipay;

import cn.moexc.vcs.domain.pay.PayDomain;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AliPay {

    private final AlipayClient alipayClient;
    private final String notifyUrl;
    private final String alipayPublicKey;

    @Autowired
    public AliPay(AlipayClient alipayClient,
                  @Value("${alipay.notifyUrl}") String notifyUrl,
                  @Value("${alipay.alipayPublicKey}") String alipayPublicKey) {
        this.alipayClient = alipayClient;
        this.notifyUrl = notifyUrl;
        this.alipayPublicKey = alipayPublicKey;
    }

    public String qr(PayDomain payDomain) {
        String bizContentJsonStr = new RequestBizContent().cvt(payDomain);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(bizContentJsonStr);
        request.setNotifyUrl(notifyUrl);

        AlipayTradePrecreateResponse tradePrecreateResponse;
        try{
            tradePrecreateResponse = alipayClient.execute(request);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        ResponseBody responseBody = JSON.parseObject(tradePrecreateResponse.getBody(), ResponseBody.class);
        ResponseBody.Response response = responseBody.getResponse();
        if ("10000".equals(response.getCode())){
            return response.getQrCode();
        }
        return null;
    }

    public boolean signature(Map<String,String> params){
        try {
            return AlipaySignature.rsaCheckV1(params, alipayPublicKey, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
