package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author yangt
 */
@Table(name="tb_return_order_item")
@Data
public class ReturnOrderItem implements Serializable{

	private static final long serialVersionUID = 3828492913356305787L;
	@Id
    @Column(name = "id")
	private Long id;

	/**
	 * 分类ID
	 */
    @Column(name = "category_id")
	private Long categoryId;

    @Column(name = "spu_id")
	private Long spuId;

    @Column(name = "sku_id")
	private Long skuId;

	/**
	 * 订单
	 */
	@Column(name = "order_id")
	private Long orderId;

	/**
	 * 订单明细ID
	 */
	@Column(name = "order_item_id")
	private Long orderItemId;

	/**
	 * 退货订单ID
	 */
    @Column(name = "return_order_id")
	private Long returnOrderId;

	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 单价
	 */
    @Column(name = "price")
	private BigDecimal price;

	/**
	 * 数量
	 */
    @Column(name = "num")
	private Integer num;

	/**
	 * 总金额
	 */
    @Column(name = "money")
	private BigDecimal money;

	/**
	 * 支付金额
	 */
    @Column(name = "pay_money")
	private BigDecimal payMoney;

	/**
	 * 图片地址
	 */
    @Column(name = "image")
	private String image;

	/**
	 * 重量
	 */
    @Column(name = "weight")
	private BigDecimal weight;


}
