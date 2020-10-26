package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_cities")
@Data
public class Cities implements Serializable{

	private static final long serialVersionUID = -1704963341806285598L;
	@Id
    @Column(name = "cityid")
	private Long cityId;

	/**
	 * //城市名称
	 */
    @Column(name = "city")
	private String city;

	/**
	 * //省份ID
	 */
	@Column(name = "provinceid")
	private Long provinceId;

}
