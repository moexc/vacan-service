package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.goods.GoodsDomainRepository;
import cn.moexc.vcs.domain.order.CreateOrderCommand;
import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.service.cmdfactory.CreaterOrderCmdFactory;
import cn.moexc.vcs.service.config.LockerException;
import cn.moexc.vcs.service.dto.CreateOrderDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RocketMQMessageListener(consumerGroup = "order-delay", topic = "topic-order-delay", selectorExpression = "*")
public class OrderService implements RocketMQListener<String> {

    private final OrderDomainRepository orderDomainRepository;
    private final GoodsDomainRepository goodsDomainRepository;
    private final RedissonClient redissonClient;

    public OrderService(OrderDomainRepository orderDomainRepository,
                        GoodsDomainRepository goodsDomainRepository,
                        RedissonClient redissonClient) {
        this.orderDomainRepository = orderDomainRepository;
        this.goodsDomainRepository = goodsDomainRepository;
        this.redissonClient = redissonClient;
    }

    /**
     * 创建订单
     * @param createOrderDTO et
     * @param cid 操作人（买方）
     * @return 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String create(CreateOrderDTO createOrderDTO, String cid){
        final String goodsId = createOrderDTO.getShopId();

        RLock lock = redissonClient.getLock(goodsId);
        if (!lock.tryLock()) throw new LockerException();
        try{
            GoodsDomain goodsDomain = goodsDomainRepository.byId(goodsId);
            CreateOrderCommand cmd = CreaterOrderCmdFactory.gen(createOrderDTO, goodsDomain, cid);

            goodsDomain.createOrder(createOrderDTO.getQuantity());
            goodsDomainRepository.save(goodsDomain);

            OrderDomain orderDomain = new OrderDomain();
            String orderId = orderDomain.create(cmd);
            orderDomainRepository.save(orderDomain);
            return orderId;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param cid 操作人
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelOne(String orderId, String cid){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);

        final String goodsId =  orderDomain.getGoodsId();
        RLock lockOrder = redissonClient.getLock(orderId);
        RLock lockGoods = redissonClient.getLock(goodsId);
        RLock lock = redissonClient.getMultiLock(lockGoods, lockOrder);
        if (!lock.tryLock()) throw new LockerException();
        try{
            GoodsDomain goodsDomain = goodsDomainRepository.byId(goodsId);

            orderDomain.cancel(cid);
            orderDomainRepository.save(orderDomain);

            goodsDomain.cancelOrder(orderDomain.getQuantity());
            goodsDomainRepository.save(goodsDomain);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 发货
     * @param orderId 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deliver(String orderId){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.deliver();
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 删除
     * @param orderId 订单ID
     * @param cid 操作人
     */
    public void deleteOne(String orderId, String cid){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.delete(cid);
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 收货
     * @param orderId 订单ID
     */
    public void accept(String orderId) {
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.accept();
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 接收到订单超时推送
     * @param orderId 订单ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onMessage(String orderId) {
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        if (!"00".equals(orderDomain.getStatus())){
            return;
        }
        final String goodsId = orderDomain.getGoodsId();
        RLock lockOrder = redissonClient.getLock(orderId);
        RLock lockGoods = redissonClient.getLock(goodsId);
        RLock lock = redissonClient.getMultiLock(lockOrder, lockGoods);
        if (!lock.tryLock()) throw new LockerException();
        try{
            GoodsDomain goodsDomain = goodsDomainRepository.byId(goodsId);
            goodsDomain.cancelOrder(orderDomain.getQuantity());

            orderDomain.expired();
            orderDomainRepository.save(orderDomain);
        }finally {
            lock.unlock();
        }
    }
}
