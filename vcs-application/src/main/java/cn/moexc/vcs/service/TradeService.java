package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.trade.CreateTradeCommand;
import cn.moexc.vcs.domain.trade.TradeDomain;
import cn.moexc.vcs.domain.trade.TradeDomainRepository;
import cn.moexc.vcs.domain.trade.UpdateTradeCommand;
import cn.moexc.vcs.domain.trade.engine.BidEnginePort;
import cn.moexc.vcs.domain.trade.engine.CreateDtoFactory;
import cn.moexc.vcs.domain.trade.engine.Engine4CreateDTO;
import cn.moexc.vcs.infrasture.jpa.entity.TradeBidEntity;
import cn.moexc.vcs.infrasture.jpa.entity.TradeEntity;
import cn.moexc.vcs.infrasture.jpa.repository.TradeBidEntityRepository;
import cn.moexc.vcs.infrasture.jpa.repository.TradeEntityRepository;
import cn.moexc.vcs.service.cmdfactory.AcceptTradeResultCommandFactory;
import cn.moexc.vcs.service.cmdfactory.CreateTradeCmdFactory;
import cn.moexc.vcs.service.cmdfactory.UpdateTradeCmdFactory;
import cn.moexc.vcs.service.dto.TradeDTO;
import cn.moexc.vcs.service.dto.SearchTradeDTO;
import cn.moexc.vcs.service.dto.TradeResultDTO;
import cn.moexc.vcs.service.vo.PageResult;
import cn.moexc.vcs.service.vo.TradeVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final TradeDomainRepository tradeDomainRepository;
    private final BidEnginePort bidEnginePort;
    private final TradeEntityRepository tradeEntityRepository;
    private final TradeBidEntityRepository tradeBidEntityRepository;

    public TradeService(TradeDomainRepository tradeDomainRepository,
                        @Qualifier("bidEnginePortImpl") BidEnginePort bidEnginePort,
                        TradeEntityRepository tradeEntityRepository,
                        TradeBidEntityRepository tradeBidEntityRepository) {
        this.tradeDomainRepository = tradeDomainRepository;
        this.bidEnginePort = bidEnginePort;
        this.tradeEntityRepository = tradeEntityRepository;
        this.tradeBidEntityRepository = tradeBidEntityRepository;
    }

    public PageResult<TradeVO> list(SearchTradeDTO searchTradeDTO, Integer page, Integer rows) {
        Specification<TradeEntity> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(searchTradeDTO.getTradeName())){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+ searchTradeDTO.getTradeName() +"%"));
            }
            if (searchTradeDTO.getTimeRangeBefore() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), searchTradeDTO.getTimeRangeBefore()));
            }
            if (searchTradeDTO.getTimeRangeAfter() != null){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(searchTradeDTO.getTimeRangeAfter());
                calendar.add(Calendar.DATE, 1);
                predicates.add(criteriaBuilder.lessThan(root.get("startTime"), calendar.getTime()));
            }
            if (!StringUtils.isEmpty(searchTradeDTO.getTradeStatus())){
                predicates.add(criteriaBuilder.equal(root.get("status"), searchTradeDTO.getTradeStatus()));
            }
            if (!StringUtils.isEmpty(searchTradeDTO.getSendStatus())){
                predicates.add(criteriaBuilder.equal(root.get("sendStatus"), searchTradeDTO.getSendStatus()));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("startTime")));
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<TradeEntity> source = tradeEntityRepository.findAll(specification, pageable);

        PageResult<TradeVO> result = new PageResult<>();
        result.setTotal(source.getTotalElements());
        result.setData(source.getContent().stream().map(TradeVO::gen).collect(Collectors.toList()));

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(TradeDTO tradeDTO){
        CreateTradeCommand command = CreateTradeCmdFactory.gen(tradeDTO);
        TradeDomain tradeDomain = new TradeDomain();
        String tradeId = tradeDomain.createTrade(command);
        tradeDomainRepository.save(tradeDomain);
        return tradeId;
    }

    public TradeDTO detail(String tradeId){
         TradeEntity tradeEntity = tradeEntityRepository.findById(tradeId).orElseThrow(() -> new AlterException("未获取到专场信息！"));
        List<TradeBidEntity> tradeBidEntities = tradeBidEntityRepository.findAllByTradeIdOrderByNumberAsc(tradeId);
        return TradeDTO.gen(tradeEntity, tradeBidEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String tradeId, TradeDTO tradeDTO) {
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeId);
        UpdateTradeCommand command = UpdateTradeCmdFactory.gen(tradeDTO);
        tradeDomain.updateTrade(command);
        tradeDomainRepository.save(tradeDomain);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String tradeId){
        tradeDomainRepository.delete(tradeId);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean send2Engine(String tradeId){
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeId);
        Engine4CreateDTO createDTO = CreateDtoFactory.gen(tradeDomain);
        boolean isSuccess = bidEnginePort.send(createDTO);
        tradeDomain.sended(isSuccess);
        tradeDomainRepository.save(tradeDomain);
        return isSuccess;
    }

    public List<TradeVO> pushed() {
        List<TradeEntity> tradeEntities = tradeEntityRepository.findAllBySendStatusAndStatusNotOrderByStartTimeAsc("1", "2");
        return tradeEntities.stream().map(TradeVO::gen).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void acceptResult(TradeResultDTO tradeResultDTO) {
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeResultDTO.getId());
        tradeDomain.acceptResult(AcceptTradeResultCommandFactory.gen(tradeResultDTO));
        tradeDomainRepository.save(tradeDomain);
    }
}
