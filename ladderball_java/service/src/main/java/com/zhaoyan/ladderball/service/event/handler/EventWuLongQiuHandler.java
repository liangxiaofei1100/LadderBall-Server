package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * | 乌龙球		| 10027		|			|
 */
public class EventWuLongQiuHandler extends EventHandler {
    Logger logger = LoggerFactory.getLogger(EventWuLongQiuHandler.class);

    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        // 事件总数
        EventOfMatchDao eventOfMatchDao = getEventOfMatchDao();
        int eventCount = eventOfMatchDao.getEventCountByPlayer(event.eventCode, event.playerId);
        // 更新个人事件个数
        PlayerOfMatchDao playerOfMatchDao = getPlayerOfMatchDao();
        PlayerOfMatch playerOfMatch = playerOfMatchDao.getPlayerByPlayerOfMatchId(event.playerId);
        playerOfMatch.event10027 = eventCount;
        playerOfMatchDao.modifyPlayer(playerOfMatch);
        // 更新对面球队得分
        long oppositeTeamId = TeamScoreUtil.getOppositeTeamId(getMatchDao(), event.matchId, event.teamId);
        logger.debug("handleEvent() oppositeTeamId = " + oppositeTeamId);
        TeamScoreUtil.updateTeamScore(getTeamOfMatchDao(), playerOfMatchDao, oppositeTeamId, event.teamId);
        return true;
    }
}
