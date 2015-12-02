package com.zhaoyan.ladderball.dao.account;

import com.zhaoyan.ladderball.domain.account.db.Recorder;

/**
 * recorder 表
 */
public interface RecorderDao {

    /**
     * 通过电话号码和密码获取recorder
     */
    Recorder getRecorderByPhone(String phone, String password);

    /**
     * 通过电话号码获取recorder
     */
    Recorder getRecorderByPhone(String phone);

    /**
     * 修改密码
     */
    boolean setPassword(long recorderId, String oldPassword, String newPassword);
}
