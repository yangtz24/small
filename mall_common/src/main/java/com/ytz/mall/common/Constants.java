package com.ytz.mall.common;

/**
 * @ClassName: Constant
 * @Description: TODO  公共类
 * @author: yangtz
 * @date: 2020/10/30
 * @Version: V1.0
 */
public class Constants {

    /**
     * 购物车
     */
    public static final String CART = "Cart_";

    /**
     * token
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 用户信息
     */
    public static final String USER_INFO = "UserInfo";

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "public.key";

    /**
     * 有效期为 60 * 60 *1000  一个小时
     */

    public static final Long JWT_TTL = 3600000L;

    /**
     * Jwt令牌信息
     */
    public static final String JWT_KEY = "mall_shopping";

}
