package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangt
 */
@Table(name="tb_order_log")
@Data
public class OrderLog implements Serializable{

	private static final long serialVersionUID = -7674736543770824827L;
	@Id
    @Column(name = "id")
	private String id;

	/**
	 * 操作员
	 */
    @Column(name = "operater")
	private String operater;

	/**
	 * 操作时间
	 */
	@Column(name = "operate_time")
	private Date operateTime;

	/**
	 * 订单ID
	 */
    @Column(name = "order_id")
	private String orderId;

	/**
	 * 订单状态,0未完成，1已完成，2，已退货
	 */
	@Column(name = "order_status")
	private Integer orderStatus;

	/**
	 * 付款状态  0:未支付，1：已支付，2：支付失败
	 */
    @Column(name = "pay_status")
	private Integer payStatus;

	/**
	 * 发货状态 0：未发货 1：已发货
	 */
	@Column(name = "consign_status")
	private Integer consignStatus;

	/**
	 * 备注
	 */
    @Column(name = "remarks")
	private String remarks;

	/**
	 * 支付金额
	 */
	@Column(name = "money")
	private BigDecimal money;

	/**
	 * 用户名
	 */
    @Column(name = "username")
	private String username;

}
