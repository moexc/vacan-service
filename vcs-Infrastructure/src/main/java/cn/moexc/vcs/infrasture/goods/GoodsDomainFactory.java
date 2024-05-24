package cn.moexc.vcs.infrasture.goods;

import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.Goods;

import java.nio.charset.StandardCharsets;

public class GoodsDomainFactory {
    public static GoodsDomain genDomain(Goods entity){
        GoodsDomain domain = new GoodsDomain();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setPhoto(entity.getPhoto());
        domain.setSubdescr(entity.getSubdescr());
        domain.setDetail(new String(entity.getDetail()));
        domain.setClassify(entity.getClassify());
        domain.setOrigPrice(entity.getOrigPrice());
        domain.setPrice(entity.getPrice());
        domain.setQuantity(entity.getQuantity());
        domain.setStatus(entity.getStatus());
        domain.setCreateTime(entity.getCreateTime());
        return domain;
    }

    public static Goods genEntity(GoodsDomain domain){
        Goods entity = new Goods();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setPhoto(domain.getPhoto());
        entity.setSubdescr(domain.getSubdescr());
        entity.setDetail(domain.getDetail().getBytes(StandardCharsets.UTF_8));
        entity.setClassify(domain.getClassify());
        entity.setOrigPrice(domain.getOrigPrice());
        entity.setPrice(domain.getPrice());
        entity.setQuantity(domain.getQuantity());
        entity.setStatus(domain.getStatus());
        entity.setCreateTime(domain.getCreateTime());
        return entity;
    }
}
