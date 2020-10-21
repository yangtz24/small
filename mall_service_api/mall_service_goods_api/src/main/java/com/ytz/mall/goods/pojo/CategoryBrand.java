package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "CategoryBrand",value = "CategoryBrand")
@Table(name="tb_category_brand")
@Data
public class CategoryBrand implements Serializable{

	private static final long serialVersionUID = 6442055025706093017L;
	/**
	 * 分类ID
	 */
	@ApiModelProperty(value = "分类ID",required = false)
	@Id
    @Column(name = "category_id")
	private Integer categoryId;

	/**
	 * 品牌ID
	 */
	@ApiModelProperty(value = "品牌ID",required = false)
	@Id
	@Column(name = "brand_id")
	private Integer brandId;

}
