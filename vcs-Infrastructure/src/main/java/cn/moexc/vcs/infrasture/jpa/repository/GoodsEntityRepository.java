package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.GoodsEntity;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.Goods4CreateOrder;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsDetail;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsEntityRepository extends JpaRepository<GoodsEntity, String>, JpaSpecificationExecutor<GoodsEntity> {

    @Query("select " +
            "new cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple(" +
            "g.id, " +
            "g.title, " +
            "g.photo, " +
            "g.subdescr, " +
            "g.origPrice, " +
            "g.price, " +
            "g.quantity" +
            ") from GoodsEntity g " +
            "where g.status = '03' " +
            "order by g.quantity desc")
    List<GoodsSimple> todayStar();

    @Query("select " +
            "new cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple(" +
            "g.id, " +
            "g.title, " +
            "g.photo, " +
            "g.subdescr, " +
            "g.origPrice, " +
            "g.price, " +
            "g.quantity, " +
            "g.status" +
            ") " +
            "from GoodsEntity g " +
            "order by g.quantity desc")
    Page<GoodsSimple> selectGoods(Pageable pageable);

    @Query("select new cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsDetail(id, title, photo, subdescr, detail, origPrice, price, quantity, status) from GoodsEntity where id = :id")
    GoodsDetail selectGoodsDetail(@Param("id") String id);

    @Query("select new cn.moexc.vcs.infrasture.jpa.entity.queryresult.Goods4CreateOrder(id, title, photo, price, quantity, status) from GoodsEntity where id = :id")
    Goods4CreateOrder selectGoods4CreateOrder(@Param("id") String id);
}