package com.zhaoyan.ladderball.domain.account.db;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "weixinuser")
public class WeiXinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    /**
     * 微信名字
     */
    @Column(name = "name")
    public String name;
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

    public static final int GENDER_NOT_SET = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;
}
