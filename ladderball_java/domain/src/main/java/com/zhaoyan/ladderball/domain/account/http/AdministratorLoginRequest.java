package com.zhaoyan.ladderball.domain.account.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

/**
 * 管理员登录
 */
public class AdministratorLoginRequest extends Request{
    /**
     * 账号
     */
    public String userName;

    /**
     * 密码
     */
    public String password;

    /**
     * 登录类型
     */
    public int loginType;

    /**
     * 登录类型，自动登录
     */
    public static final int LOGIN_TYPE_AUTO = 0;
    /**
     * 登录类型，密码登录
     */
    public static final int LOGIN_TYPE_PASSWORD = 1;

    @Override
    public String toString() {
        return "AdministratorLoginRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginType=" + loginType +
                '}';
    }
}
