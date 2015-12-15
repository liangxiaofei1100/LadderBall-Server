package com.zhaoyan.ladderball.dao.eventofmatch;


import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;

import java.util.List;

public interface EventOfMatchDao {
    /**
     * 事件的uuid是否重复
     */
    boolean isEventUUIDRepeated(String uuid);

    /**
     * 添加一个事件
     */
    boolean addEvent(EventOfMatch eventOfMatch);

    /**
     * 更新事件
     */
    void updateEvent(EventOfMatch eventOfMatch);

    /**
     * 获取球员的一个事件总数
     */
    int getEventCountByPlayer(int eventCode, long playerId);

    /**
     * 获取比赛一小节的事件
     */
    List<EventOfMatch> getEventPartList(long matchId, long teamId, int partNumber);

    /**
     * 删除比赛一小节中的小节结束事件
     */
    void deleteXiaoJieJieShuEvent(long matchId, long teamId, int partNumber);

    /**
     * 根据id获取事件
     */
    EventOfMatch getEventBy(long eventId);

    /**
     * 删除一个事件
     */
    void deleteEvent(EventOfMatch eventOfMatch);
}
