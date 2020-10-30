package com.ytz.mall.goods.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author yangt
 */
@ApiModel(description = "Sku",value = "Sku")
@Table(name="tb_sku")
@Data
public class Sku implements Serializable{

	private static final long serialVersionUID = 7466905750060491583L;
	@ApiModelProperty(value = "商品id",required = false)
	@Id
    @Column(name = "id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "商品条码",required = false)
    @Column(name = "sn")
	private String sn;

	@ApiModelProperty(value = "SKU名称",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "价格（分）",required = false)
    @Column(name = "price")
	private BigDecimal price;

	@ApiModelProperty(value = "库存数量",required = false)
    @Column(name = "num")
	private Integer num;

	@ApiModelProperty(value = "库存预警数量",required = false)
    @Column(name = "alert_num")
	private Integer alertNum;

	@ApiModelProperty(value = "商品图片",required = false)
    @Column(name = "image")
	private String image;

	@ApiModelProperty(value = "商品图片列表",required = false)
    @Column(name = "images")
	private String images;

	@ApiModelProperty(value = "重量（千克）",required = false)
    @Column(name = "weight")
	private BigDecimal weight;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "update_time")
	private Date updateTime;

	@ApiModelProperty(value = "SPUID",required = false)
    @Column(name = "spu_id")
	private Long spuId;

	@ApiModelProperty(value = "类目ID",required = false)
    @Column(name = "category_id")
	private Long categoryId;

	@ApiModelProperty(value = "类目名称",required = false)
    @Column(name = "category_name")
	private String categoryName;

	@ApiModelProperty(value = "品牌名称",required = false)
    @Column(name = "brand_name")
	private String brandName;

	@ApiModelProperty(value = "规格",required = false)
    @Column(name = "spec")
	private String spec;

	@ApiModelProperty(value = "销量",required = false)
    @Column(name = "sale_num")
	private Integer saleNum;

	@ApiModelProperty(value = "评论数",required = false)
    @Column(name = "comment_num")
	private Integer commentNum;

	@ApiModelProperty(value = "商品状态 1-正常，2-下架，3-删除",required = false)
    @Column(name = "status")
	private String status;

}
