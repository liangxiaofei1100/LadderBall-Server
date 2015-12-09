package com.zhaoyan.ladderball.dao.eventofmatch;


import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;

public interface TmpEventOfMatchDao {
    /**
     * 添加一个事件
     */
    void addEvent(TmpEventOfMatch eventOfMatch);
}
