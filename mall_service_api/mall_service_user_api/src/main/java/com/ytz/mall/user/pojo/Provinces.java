package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_provinces")
@Data
public class Provinces implements Serializable{

	private static final long serialVersionUID = -7490769422395602453L;
	/**
	 * //省份ID
	 */
	@Id
    @Column(name = "provinceid")
	private Long provinceId;

	/**
	 * //省份名称
	 */
    @Column(name = "province")
	private String province;


}
