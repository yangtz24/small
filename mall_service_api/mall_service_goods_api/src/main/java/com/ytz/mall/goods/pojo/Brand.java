package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description = "Brand",value = "Brand")
@Table(name="tb_brand")
@Data
public class Brand implements Serializable{

	private static final long serialVersionUID = -3149350387766389606L;
	/**
	 * 品牌id
	 */
	@ApiModelProperty(value = "品牌id",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**
	 * 品牌名称
	 */
	@ApiModelProperty(value = "品牌名称",required = false)
    @Column(name = "name")
	private String name;

	/**
	 * 品牌图片地址
	 */
	@ApiModelProperty(value = "品牌图片地址",required = false)
    @Column(name = "image")
	private String image;

	/**
	 * 品牌的首字母
	 */
	@ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name = "letter")
	private String letter;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
	private Integer seq;

}
