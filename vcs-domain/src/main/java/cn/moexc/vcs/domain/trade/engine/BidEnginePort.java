package cn.moexc.vcs.domain.trade.engine;

public interface BidEnginePort {
    boolean send(Engine4CreateDTO engine4CreateDTO);
}
