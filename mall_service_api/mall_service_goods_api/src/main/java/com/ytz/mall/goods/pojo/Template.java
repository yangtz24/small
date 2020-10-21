package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@ApiModel(description = "Template",value = "Template")
@Table(name="tb_template")
@Data
public class Template implements Serializable{

	private static final long serialVersionUID = -2228742459261120290L;
	@ApiModelProperty(value = "ID",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "模板名称",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "规格数量",required = false)
    @Column(name = "spec_num")
	private Integer specNum;

	@ApiModelProperty(value = "参数数量",required = false)
    @Column(name = "para_num")
	private Integer paraNum;

}
