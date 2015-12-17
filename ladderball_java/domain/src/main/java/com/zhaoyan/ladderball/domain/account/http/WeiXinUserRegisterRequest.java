package com.zhaoyan.ladderball.domain.account.http;


import com.zhaoyan.ladderball.domain.common.http.Request;


public class WeiXinUserRegisterRequest extends Request{
    /**
     * 微信id
     */
    public String weiXinId;

    public String nickName;

    public String photo;

    public int gender;

    public long birthday;

    public String address;

    public float weight;

    public float height;

    public String goodPosition;

    public String goodFoot;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return super.toString() + "WeiXinUserRegisterRequest{" +
                "weiXinId='" + weiXinId + '\'' +
                '}';
    }
}
