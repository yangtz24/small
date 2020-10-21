package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@ApiModel(description = "Para",value = "Para")
@Table(name="tb_para")
@Data
public class Para implements Serializable{

	private static final long serialVersionUID = 2858143208992404677L;
	@ApiModelProperty(value = "id",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "名称",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "选项",required = false)
    @Column(name = "options")
	private String options;

	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
	private Integer seq;

	@ApiModelProperty(value = "模板ID",required = false)
    @Column(name = "template_id")
	private Long templateId;

}
