package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Table(name="tb_address")
@Data
public class Address implements Serializable{

	private static final long serialVersionUID = 9118619431593921567L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**
	 * //用户名
	 */
    @Column(name = "username")
	private String username;

	/**
	 * //省
	 */
	@Column(name = "provinceid")
	private String provinceId;

	/**
	 * //市
	 */
    @Column(name = "cityid")
	private String cityId;

	/**
	 * //县/区
	 */
	@Column(name = "areaid")
	private String areaId;

	/**
	 * //电话
	 */
    @Column(name = "phone")
	private String phone;

	/**
	 * //详细地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * //联系人
	 */
    @Column(name = "contact")
	private String contact;

	/**
	 * //是否是默认 1默认 0否
	 */
	@Column(name = "is_default")
	private String isDefault;

	/**
	 * //别名
	 */
    @Column(name = "alias")
	private String alias;


}
