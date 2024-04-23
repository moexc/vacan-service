package cn.moexc.vcs.infrasture.trade.engine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineResponseEntity {
    private Integer code;
    private String msg;
    private Object data;
}
