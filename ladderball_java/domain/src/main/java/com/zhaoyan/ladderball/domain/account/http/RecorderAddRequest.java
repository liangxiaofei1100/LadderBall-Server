package com.zhaoyan.ladderball.domain.account.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class RecorderAddRequest extends Request {
    /**
     * 电话号码
     */
    public String phone;

    /**
     * 密码
     */
    public String password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return super.toString() + "RecorderAddRequest{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
