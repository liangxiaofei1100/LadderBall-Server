package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;

public class EventJinQiuHandler extends EventHandler{

    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        // 获取进球总个数
        EventOfMatchDao eventOfMatchDao = getEventOfMatchDao();
        int jinQiuCount = eventOfMatchDao.getEventCountByPlayer(event.eventCode, event.playerId);
        // 更新进球个数
        PlayerOfMatchDao playerOfMatchDao = getPlayerOfMatchDao();
        PlayerOfMatch playerOfMatch =  playerOfMatchDao.getPlayerByPlayerOfMatchId(event.playerId);
        playerOfMatch.event10001 = jinQiuCount;
        playerOfMatchDao.modifyPlayer(playerOfMatch);
        return true;

    }
}
