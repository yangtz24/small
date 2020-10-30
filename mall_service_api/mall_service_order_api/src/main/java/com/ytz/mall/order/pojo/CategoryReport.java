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
@Table(name="tb_category_report")
@Data
public class CategoryReport implements Serializable{

	private static final long serialVersionUID = -7510550251189999645L;
	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	private Long id;
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

	/**
	 * 统计日期
	 */
    @Column(name = "count_date")
	private Date countDate;

	/**
	 * 销售数量
	 */
    @Column(name = "num")
	private Integer num;

	/**
	 * 销售额
	 */
	@Column(name = "money")
	private BigDecimal money;


}
