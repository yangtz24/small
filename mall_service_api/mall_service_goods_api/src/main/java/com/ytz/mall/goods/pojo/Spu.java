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


@ApiModel(description = "Spu",value = "Spu")
@Table(name="tb_spu")
@Data
public class Spu implements Serializable{

	private static final long serialVersionUID = -1578169488072718998L;

	@ApiModelProperty(value = "主键",required = false)
	@JsonSerialize(using = ToStringSerializer.class)
	@Id
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "货号",required = false)
    @Column(name = "sn")
	private String sn;

	@ApiModelProperty(value = "SPU名",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "副标题",required = false)
    @Column(name = "caption")
	private String caption;

	@ApiModelProperty(value = "品牌ID",required = false)
    @Column(name = "brand_id")
	private Long brandId;

	@ApiModelProperty(value = "一级分类",required = false)
    @Column(name = "category1_id")
	private Long categoryId;

	@ApiModelProperty(value = "二级分类",required = false)
    @Column(name = "category2_id")
	private Long categorySecondId;

	@ApiModelProperty(value = "三级分类",required = false)
    @Column(name = "category3_id")
	private Long categoryThirdId;

	@ApiModelProperty(value = "模板ID",required = false)
    @Column(name = "template_id")
	private Long templateId;

	@ApiModelProperty(value = "运费模板id",required = false)
    @Column(name = "freight_id")
	private Long freightId;

	@ApiModelProperty(value = "图片",required = false)
    @Column(name = "image")
	private String image;

	@ApiModelProperty(value = "图片列表",required = false)
    @Column(name = "images")
	private String images;

	@ApiModelProperty(value = "售后服务",required = false)
    @Column(name = "sale_service")
	private String saleService;

	@ApiModelProperty(value = "介绍",required = false)
    @Column(name = "introduction")
	private String introduction;

	@ApiModelProperty(value = "规格列表",required = false)
    @Column(name = "spec_items")
	private String specItems;

	@ApiModelProperty(value = "参数列表",required = false)
    @Column(name = "para_items")
	private String paraItems;

	@ApiModelProperty(value = "销量",required = false)
    @Column(name = "sale_num")
	private Integer saleNum;

	@ApiModelProperty(value = "评论数",required = false)
    @Column(name = "comment_num")
	private Integer commentNum;

	@ApiModelProperty(value = "是否上架",required = false)
    @Column(name = "is_marketable")
	private String isMarketable;

	@ApiModelProperty(value = "是否启用规格",required = false)
    @Column(name = "is_enable_spec")
	private String isEnableSpec;

	@ApiModelProperty(value = "是否删除",required = false)
    @Column(name = "is_delete")
	private String isDelete;

	@ApiModelProperty(value = "审核状态",required = false)
    @Column(name = "status")
	private String status;

}
