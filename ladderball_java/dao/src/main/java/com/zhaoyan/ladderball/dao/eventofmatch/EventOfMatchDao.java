package com.zhaoyan.ladderball.dao.eventofmatch;


import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;

public interface EventOfMatchDao {
    /**
     * 添加一个事件
     */
    boolean addEvent(EventOfMatch eventOfMatch);

    /**
     * 获取球员的一个事件总数
     */
    int getEventCountByPlayer(int eventCode, long playerId);
}
