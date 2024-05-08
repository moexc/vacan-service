package cn.moexc.vcs.infrasture.goods;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.goods.GoodsDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.GoodsEntity;
import cn.moexc.vcs.infrasture.jpa.repository.GoodsEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {

    private final GoodsEntityRepository goodsEntityRepository;

    public GoodsDomainRepositoryImpl(GoodsEntityRepository goodsEntityRepository) {
        this.goodsEntityRepository = goodsEntityRepository;
    }

    @Override
    public GoodsDomain byId(String id) {
        GoodsEntity goodsEntity = goodsEntityRepository.findById(id).orElseThrow(() -> new AlterException("获取商品信息失败"));
        return GoodsDomainFactory.genDomain(goodsEntity);
    }

    @Override
    public void save(GoodsDomain domain) {
        GoodsEntity entity = GoodsDomainFactory.genEntity(domain);
        goodsEntityRepository.save(entity);
    }
}
