package com.zhaoyan.ladderball.dao.eventofmatch;


import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;

import java.util.List;

public interface TmpEventOfMatchDao {

    /**
     * 根据id获取事件
     */
    TmpEventOfMatch getEventBy(long eventId);

    /**
     * 添加一个事件
     */
    void addEvent(TmpEventOfMatch eventOfMatch);

    /**
     * 更新事件
     */
    void updateEvent(TmpEventOfMatch eventOfMatch);

    /**
     * 删除一个事件
     */
    void deleteEvent(TmpEventOfMatch eventOfMatch);

    /**
     * 获取球员的一个事件总数
     */
    int getEventCountByPlayer(int eventCode, long playerId);

    /**
     * 获取比赛一小节的事件
     */
    List<TmpEventOfMatch> getEventPartList(long matchId, long teamId, int partNumber);

    /**
     * 删除比赛一小节中的小节结束事件
     */
    void deleteXiaoJieJieShuEvent(long matchId, long teamId, int partNumber);

    /**
     * 事件的uuid是否重复
     */
    boolean isEventUUIDRepeated(String uuid);
}
