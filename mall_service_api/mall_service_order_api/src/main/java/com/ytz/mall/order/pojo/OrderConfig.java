package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author yangt
 */
@Table(name="tb_order_config")
@Data
public class OrderConfig implements Serializable{

	private static final long serialVersionUID = -7946860018913591386L;
	@Id
    @Column(name = "id")
	private Long id;

	/**
	 * 正常订单超时时间（分）
	 */
    @Column(name = "order_timeout")
	private Integer orderTimeout;

	/**
	 * 秒杀订单超时时间（分）
	 */
	@Column(name = "seckill_timeout")
	private Integer seckillTimeout;

	/**
	 * 自动收货（天）
	 */
    @Column(name = "auto_take")
	private Integer autoTake;

	/**
	 * 售后期限
	 */
    @Column(name = "service_deadline")
	private Integer serviceDeadline;

	/**
	 * 自动五星好评
	 */
    @Column(name = "auto_comment")
	private Integer autoComment;


}
