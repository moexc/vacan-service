package cn.moexc.vcs.infrasture.pay.alipay;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody {
    @JSONField(name = "alipay_trade_precreate_response")
    private Response response;

    @Getter
    @Setter
    static class Response {
        private String code;
        @JSONField(name = "qr_code")
        private String qrCode;
    }

}
