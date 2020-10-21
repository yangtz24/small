package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品组合实体类
 */
@ApiModel(description = "商品信息")
@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = 1587070275336477662L;
    //SPU
    private Spu spu;
    //SKU集合
    private List<Sku> skuList;

}