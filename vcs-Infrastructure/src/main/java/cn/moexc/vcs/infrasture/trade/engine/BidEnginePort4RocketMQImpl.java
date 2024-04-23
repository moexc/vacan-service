package cn.moexc.vcs.infrasture.trade.engine;

import cn.moexc.vcs.domain.trade.engine.BidEnginePort;
import cn.moexc.vcs.domain.trade.engine.Engine4CreateDTO;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class BidEnginePort4RocketMQImpl implements BidEnginePort {

    private final RocketMQTemplate rocketMQTemplate;

    public BidEnginePort4RocketMQImpl(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public boolean send(Engine4CreateDTO engine4CreateDTO) {
        try{
            Message<String> msg = MessageBuilder.withPayload(JSON.toJSONString(engine4CreateDTO)).build();
            rocketMQTemplate.send("engine", msg);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
