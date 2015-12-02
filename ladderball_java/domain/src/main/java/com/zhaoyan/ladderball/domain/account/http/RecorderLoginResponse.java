package com.zhaoyan.ladderball.domain.account.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

/**
 * 录入工具登录返回结果
 */
public class RecorderLoginResponse extends Response{
    /**
     * 用户Token，用于登录后请求
     */
    public String userToken;
    /**
     * 电话号码
     */
    public String phone;
    /**
     * 姓名
     */
    public String name;
    /**
     * 地址
     */
    public String address;
    /**
     * 性别
     */
    public int gender;

    /**
     * 性别：未知
     */
    public static final int GENDER_UNKNOWN = 0;
    /**
     * 性别：男
     */
    public static final int GENDER_MALE = 1;
    /**
     * 性别：女
     */
    public static final int GENDER_FEMALE = 2;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
