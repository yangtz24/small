package com.ytz.mall.order.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author yangt
 */
@Table(name="tb_order_item")
@Data
public class OrderItem implements Serializable{

	private static final long serialVersionUID = -2073943655553185226L;
	@Id
    @Column(name = "order_item_id")
	private String orderItemId;

	/**
	 * 1级分类
	 */
	@Column(name = "category_id")
	private Long categoryId;

	/**
	 * 2级分类
	 */
	@Column(name = "category_second_id")
	private Long categorySecondId;

	/**
	 * 3级分类
	 */
	@Column(name = "category_third_id")
	private Long categoryThirdId;


    @Column(name = "spu_id")
	private Long spuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "sku_id")
	private Long skuId;

	/**
	 * 订单ID
	 */
    @Column(name = "order_id")
	private String orderId;

	/**
	 * 商品名称
	 */
    @Column(name = "name")
	private String name;

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
	 * 实付金额
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
	private Integer weight;

	/**
	 * 运费
	 */
    @Column(name = "post_fee")
	private Integer postFee;

	/**
	 * 是否退货,0:未退货，1：已退货
	 */
    @Column(name = "is_return")
	private Integer isReturn;


}
