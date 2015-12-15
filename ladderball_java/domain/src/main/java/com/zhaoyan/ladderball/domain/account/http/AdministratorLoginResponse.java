package com.zhaoyan.ladderball.domain.account.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

/**
 * 管理员登录返回结果
 */
public class AdministratorLoginResponse extends Response {
    /**
     * 用户Token，用于登录后请求
     */
    public String userToken;
    /**
     * 姓名
     */
    public String name;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
