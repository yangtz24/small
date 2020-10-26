package com.ytz.mall.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name="oauth_client_details")
@Data
public class OauthClientDetails implements Serializable{

	private static final long serialVersionUID = -2807933053431502298L;

	/**
	 * 客户端ID，主要用于标识对应的应用
	 */
	@Id
    @Column(name = "client_id")
	private String clientId;

	/**
	 * 路径
	 */
    @Column(name = "resource_ids")
	private String resourceIds;

	/**
	 * //客户端秘钥，BCryptPasswordEncoder加密算法加密
	 */
	@Column(name = "client_secret")
	private String clientSecret;

	/**
	 * //对应的范围
	 */
    @Column(name = "scope")
	private String scope;

	/**
	 * //认证模式
	 */
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;

	/**
	 * //认证后重定向地址
	 */
    @Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;

	/**
	 * 认证信息
	 */
	@Column(name = "authorities")
	private String authorities;

	/**
	 * //令牌有效期
	 */
    @Column(name = "access_token_validity")
	private Integer accessTokenValidity;

	/**
	 * //令牌刷新周期
	 */
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;

	/**
	 *
	 */
    @Column(name = "additional_information")
	private String additionalInformation;

	/**
	 *
	 */
	@Column(name = "autoapprove")
	private String autoapprove;

}
