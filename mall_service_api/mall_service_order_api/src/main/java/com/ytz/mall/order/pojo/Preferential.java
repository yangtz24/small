package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author yangt
 */
@Table(name="tb_preferential")
@Data
public class Preferential implements Serializable{

	private static final long serialVersionUID = -6526636548237222747L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**
	 * 消费金额
	 */
    @Column(name = "buy_money")
	private BigDecimal buyMoney;

	/**
	 * 优惠金额
	 */
	@Column(name = "pre_money")
	private BigDecimal preMoney;

	/**
	 * 品类ID
	 */
    @Column(name = "category_id")
	private Long categoryId;

	/**
	 * 活动开始日期
	 */
	@Column(name = "start_time")
	private Date startTime;

	/**
	 * 活动截至日期
	 */
    @Column(name = "end_time")
	private Date endTime;

	/**
	 * 状态
	 */
	@Column(name = "state")
	private String state;

	/**
	 * 类型1不翻倍 2翻倍
	 */
    @Column(name = "type")
	private String type;


}
