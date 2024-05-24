package cn.moexc.vcs.infrasture.goods;

import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.goods.GoodsDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Goods;
import cn.moexc.vcs.infrasture.mybatis.mapper.GoodsMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {

    private final GoodsMapper goodsMapper;

    public GoodsDomainRepositoryImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public GoodsDomain byId(String id) {
        return GoodsDomainFactory.genDomain(goodsMapper.selectByPrimaryKey(id));
    }

    @Override
    public void save(GoodsDomain domain) {
        Goods newEntity = GoodsDomainFactory.genEntity(domain);
        Goods entity = goodsMapper.selectByPrimaryKey(newEntity.getId());
        if (entity == null) goodsMapper.insert(newEntity);
        else goodsMapper.updateByPrimaryKey(newEntity);
    }
}
