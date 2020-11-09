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
@Table(name="tb_order")
@Data
public class Order implements Serializable{

	private static final long serialVersionUID = -5992996247121526948L;
	@Id
    @Column(name = "order_id")
	private String orderId;

	/**
	 * 数量合计
	 */
    @Column(name = "total_num")
	private Integer totalNum;

	/**
	 * 金额合计
	 */
    @Column(name = "total_money")
	private BigDecimal totalMoney;

	/**
	 * 优惠金额
	 */
    @Column(name = "pre_money")
	private BigDecimal preMoney;

	/**
	 * 邮费
	 */
    @Column(name = "post_fee")
	private BigDecimal postFee;

	/**
	 * 实付金额
	 */
    @Column(name = "pay_money")
	private BigDecimal payMoney;

	/**
	 * 支付类型，1、在线支付、0 货到付款
	 */
    @Column(name = "pay_type")
	private String payType;

	/**
	 * 订单创建时间
	 */
    @Column(name = "create_time")
	private Date createTime;

	/**
	 * 订单更新时间
	 */
    @Column(name = "update_time")
	private Date updateTime;

	/**
	 * 付款时间
	 */
    @Column(name = "pay_time")
	private Date payTime;

	/**
	 * 发货时间
	 */
    @Column(name = "consign_time")
	private Date consignTime;

	/**
	 * 交易完成时间
	 */
    @Column(name = "end_time")
	private Date endTime;

	/**
	 * 交易关闭时间
	 */
    @Column(name = "close_time")
	private Date closeTime;

	/**
	 * 物流名称
	 */
    @Column(name = "shipping_name")
	private String shippingName;

	/**
	 * 物流单号
	 */
    @Column(name = "shipping_code")
	private String shippingCode;

	/**
	 * 用户名称
	 */
    @Column(name = "username")
	private String username;

	/**
	 * 买家留言
	 */
    @Column(name = "buyer_message")
	private String buyerMessage;

	/**
	 * 是否评价
	 */
    @Column(name = "buyer_rate")
	private String buyerRate;

	/**
	 * 收货人
	 */
    @Column(name = "receiver_contact")
	private String receiverContact;

	/**
	 * 收货人手机
	 */
    @Column(name = "receiver_mobile")
	private String receiverMobile;

	/**
	 * 收货人地址
	 */
    @Column(name = "receiver_address")
	private String receiverAddress;

	/**
	 * 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
	 */
    @Column(name = "source_type")
	private Integer sourceType;

	/**
	 * 交易流水号
	 */
    @Column(name = "transaction_id")
	private String transactionId;

	/**
	 * 订单状态,0:未完成,1:已完成，2：已退货
	 */
    @Column(name = "order_status")
	private Integer orderStatus;

	/**
	 * 支付状态,0:未支付，1：已支付，2：支付失败
	 */
    @Column(name = "pay_status")
	private Integer payStatus;

	/**
	 * 发货状态,0:未发货，1：已发货，2：已收货
	 */
    @Column(name = "consign_status")
	private Integer consignStatus;

	/**
	 * 是否删除 0未删除 1已删除
	 */
    @Column(name = "is_delete")
	private Integer isDelete;
}
