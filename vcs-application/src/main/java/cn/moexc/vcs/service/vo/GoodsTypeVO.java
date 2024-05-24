package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.mybatis.entity.GoodClassify;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsTypeVO {
    private String typeId;
    private String typeName;

    public static GoodsTypeVO gen(GoodClassify entity){
        GoodsTypeVO goodsTypeVO = new GoodsTypeVO();
        goodsTypeVO.setTypeId(entity.getCode());
        goodsTypeVO.setTypeName(entity.getName());
        return goodsTypeVO;
    }
}
