package com.ytz.mall.common;

/**
 * 返回码
 */
public class StatusCode {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    /**
     * 失败
     */
    public static final int ERROR = 500;
    /**
     * 用户名或密码错误
     */
    public static final int LOGINERROR = 2001;
    /**
     * 权限不足
     */
    public static final int ACCESSERROR = 403;
    /**
     * 远程调用失败
     */
    public static final int REMOTEERROR = 501;
    /**
     * 重复操作
     */
    public static final int REPERROR = 601;
    /**
     * 没有对应的抢购数据
     */
    public static final int NOTFOUNDERROR = 701;
}
