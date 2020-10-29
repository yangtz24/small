package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name="tb_user")
@Data
public class User implements Serializable{

	private static final long serialVersionUID = -9214960101019168096L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	/**
	 * //用户名
	 */
    @Column(name = "username")
	private String userName;

	/**
	 * //密码，加密存储
	 */
    @Column(name = "password")
	private String password;

	/**
	 * //注册手机号
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * //注册邮箱
	 */
    @Column(name = "email")
	private String email;

	/**
	 * //创建时间
	 */
	@Column(name = "created")
	private Date created;

	/**
	 * //修改时间
	 */
    @Column(name = "updated")
	private Date updated;

	/**
	 * //会员来源：1:PC，2：H5，3：Android，4：IOS
	 */
	@Column(name = "source_type")
	private String sourceType;

	/**
	 * //昵称
	 */
    @Column(name = "nick_name")
	private String nickName;

	/**
	 * //真实姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * //使用状态（1正常 0非正常）
	 */
    @Column(name = "status")
	private String status;

	/**
	 * //头像地址
	 */
	@Column(name = "head_pic")
	private String headPic;

	/**
	 * //QQ号码
	 */
    @Column(name = "qq")
	private String qq;

	/**
	 * //手机是否验证 （0否  1是）
	 */
	@Column(name = "is_mobile_check")
	private String isMobileCheck;

	/**
	 * //邮箱是否检测（0否  1是）
	 */
    @Column(name = "is_email_check")
	private String isEmailCheck;

	/**
	 * //性别，1男，0女
	 */
	@Column(name = "sex")
	private String sex;

	/**
	 * //会员等级
	 */
    @Column(name = "user_level")
	private Integer userLevel;

	/**
	 * //积分
	 */
	@Column(name = "points")
	private Integer points;

	/**
	 * //经验值
	 */
    @Column(name = "experience_value")
	private Integer experienceValue;

	/**
	 * //出生年月日
	 */
	@Column(name = "birthday")
	private Date birthday;

	/**
	 * //最后登录时间
	 */
    @Column(name = "last_login_time")
	private Date lastLoginTime;

}
