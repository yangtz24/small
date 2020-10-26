package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="tb_areas")
public class Areas implements Serializable{

	private static final long serialVersionUID = -2388849020701969347L;
	@Id
    @Column(name = "areaid")
	private Long areaId;

	/**
	 * //区域名称
	 */
    @Column(name = "area")
	private String area;

	/**
	 * //城市ID
	 */
	@Column(name = "cityid")
	private Long cityId;

}
