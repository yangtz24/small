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
@Table(name="tb_return_order")
@Data
public class ReturnOrder implements Serializable{

	private static final long serialVersionUID = -5919035046181892137L;
	@Id
    @Column(name = "id")
	private Long id;

	/**
	 * 订单号
	 */
    @Column(name = "order_id")
	private Long orderId;

	/**
	 * 申请时间
	 */
    @Column(name = "apply_time")
	private Date applyTime;

	/**
	 * 用户ID
	 */
    @Column(name = "user_id")
	private Long userId;

	/**
	 * 用户账号
	 */
    @Column(name = "user_account")
	private String userAccount;

	/**
	 * 联系人
	 */
    @Column(name = "contact")
	private String contact;

	/**
	 * 联系人手机
	 */
    @Column(name = "linkman_mobile")
	private String linkmanMobile;

	/**
	 * 类型
	 */
    @Column(name = "type")
	private String type;

	/**
	 * 退款金额
	 */
    @Column(name = "return_money")
	private BigDecimal returnMoney;

	/**
	 * 是否退运费 0是1否
	 */
    @Column(name = "is_return_freight")
	private Integer isReturnFreight;

	/**
	 * 申请状态  0:未申请 1:正在审核 2:申请成功 3：拒绝
	 */
    @Column(name = "status")
	private Integer status;

	/**
	 * 处理时间
	 */
    @Column(name = "dispose_time")
	private Date disposeTime;

	/**
	 * 退货退款原因
	 */
    @Column(name = "return_cause")
	private Integer returnCause;

	/**
	 * 凭证图片
	 */
    @Column(name = "evidence")
	private String evidence;

	/**
	 * 问题描述
	 */
    @Column(name = "description")
	private String description;

	/**
	 * 处理备注
	 */
    @Column(name = "remark")
	private String remark;

	/**
	 * 管理员id
	 */
    @Column(name = "admin_id")
	private Integer adminId;


}
