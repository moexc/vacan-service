package cn.moexc.vcs.infrasture.trade.engine;

import cn.moexc.vcs.domain.trade.engine.BidEnginePort;
import cn.moexc.vcs.domain.trade.engine.Engine4CreateDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BidEnginePortImpl implements BidEnginePort {

    private final RestTemplate restTemplate;
    private final String engineCreate;
    private final String resultAcceptUri;

    public BidEnginePortImpl(RestTemplate restTemplate,
                             @Value("${bid.engine-server}") String bidEngineServer,
                             @Value("${bid.method-create}") String engineCreate,
                             @Value("${bid.result-accept-uri}") String resultAcceptUri) {
        this.restTemplate = restTemplate;
        this.engineCreate = bidEngineServer + engineCreate;
        this.resultAcceptUri = resultAcceptUri;
    }

    @Override
    public boolean send(Engine4CreateDTO engine4CreateDTO) {
        engine4CreateDTO.setResultPushAddress(resultAcceptUri);
        EngineResponseEntity responseEntity = restTemplate.postForObject(engineCreate, engine4CreateDTO, EngineResponseEntity.class);
        if (responseEntity == null){
            return false;
        }
        return responseEntity.getCode() == 0;
    }
}
