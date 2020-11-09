package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author yangt
 */
@Table(name="tb_return_cause")
@Data
public class ReturnCause implements Serializable{

	private static final long serialVersionUID = -1870954561848173722L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**
	 * 原因
	 */
    @Column(name = "cause")
	private String cause;

	/**
	 * 排序
	 */
	@Column(name = "seq")
	private Integer seq;

	/**
	 * 是否启用 0是 1否
	 */
    @Column(name = "status")
	private Integer status;


}
