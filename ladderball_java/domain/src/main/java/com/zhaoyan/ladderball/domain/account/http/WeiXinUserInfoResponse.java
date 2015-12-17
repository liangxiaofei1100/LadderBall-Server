package com.zhaoyan.ladderball.domain.account.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.Date;

public class WeiXinUserInfoResponse extends Response{

    /**
     * 用户id
     */
    public long id;
    /**
     * 微信id
     */
    public String weiXinId;
    /**
     * 昵称
     */
    public String nickName;
    /**
     * 手机
     */
    public String phone;
    /**
     * 性别
     */
    public int gender;
    /**
     * 生日
     */
    public long birthday;
    /**
     * 体重，单位kg
     */
    public float weight;
    /**
     * 身高，单位cm
     */
    public float height;
    /**
     * 擅长位置
     */
    public String goodPosition;
    /**
     * 惯用脚
     */
    public String goodFoot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeiXinId() {
        return weiXinId;
    }

    public void setWeiXinId(String weiXinId) {
        this.weiXinId = weiXinId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getGoodPosition() {
        return goodPosition;
    }

    public void setGoodPosition(String goodPosition) {
        this.goodPosition = goodPosition;
    }

    public String getGoodFoot() {
        return goodFoot;
    }

    public void setGoodFoot(String goodFoot) {
        this.goodFoot = goodFoot;
    }
}
