package com.zhaoyan.ladderball.domain.account.db;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "weixinuser")
public class WeiXinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    /**
     * 微信id
     */
    @Column(name = "weixinid")
    public String weiXinId;
    /**
     * 昵称
     */
    @Column(name = "nickName")
    public String nickName;
    /**
     * 手机
     */
    @Column(name = "phone")
    public String phone;
    /**
     * 性别
     */
    @Column(name = "gender")
    public int gender;
    /**
     * 生日
     */
    @Column(name = "birthday")
    public Date birthday;
    /**
     * 地区
     */
    @Column(name = "address")
    public String address;
    /**
     * 体重，单位kg
     */
    @Column(name = "weight_kg")
    public float weight;
    /**
     * 身高，单位cm
     */
    @Column(name = "height_cm")
    public float height;
    /**
     * 擅长位置
     */
    @Column(name = "good_position")
    public String goodPosition;
    /**
     * 惯用脚
     */
    @Column(name = "good_foot")
    public String goodFoot;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    public Date createTime;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    public Date lastLoginTime;

    public static final int GENDER_NOT_SET = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
        return "WeiXinUser{" +
                "id=" + id +
                ", weiXinId='" + weiXinId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", height=" + height +
                ", goodPosition='" + goodPosition + '\'' +
                ", goodFoot='" + goodFoot + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
