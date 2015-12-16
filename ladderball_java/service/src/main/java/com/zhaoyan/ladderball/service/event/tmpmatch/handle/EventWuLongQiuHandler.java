package com.zhaoyan.ladderball.service.event.tmpmatch.handle;

import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * | 乌龙球		| 10027		|			|
 */
public class EventWuLongQiuHandler extends EventHandler {
    Logger logger = LoggerFactory.getLogger(EventWuLongQiuHandler.class);

    @Override
    public boolean handleAddEvent(TmpEventOfMatch event) {
        if (event.playerOfMatch != null) {
            // 更新球员数据
            updateplayerOfMatch(event.eventCode, event.playerOfMatch.id);
            // 更新对面球队得分
            updateOppositeTeamScore(event.matchId, event.teamId);
        }
        return true;
    }

    @Override
    public boolean handleDeleteEvent(TmpEventOfMatch event) {
        if (event.playerOfMatch != null) {
            // 更新球员数据
            updateplayerOfMatch(event.eventCode, event.playerOfMatch.id);
            // 更新对面球队得分
            updateOppositeTeamScore(event.matchId, event.teamId);
        }
        return true;
    }

    /**
     * 更新球员数据
     */
    private void updateplayerOfMatch(int eventCode, long playerId) {
        // 事件总数
        TmpEventOfMatchDao eventOfMatchDao = getEventOfMatchDao();
        int eventCount = eventOfMatchDao.getEventCountByPlayer(eventCode, playerId);
        // 更新个人事件个数
        TmpPlayerOfMatchDao playerOfMatchDao = getPlayerOfMatchDao();
        TmpPlayerOfMatch playerOfMatch = playerOfMatchDao.getPlayerById(playerId);
        playerOfMatch.event10027 = eventCount;
        playerOfMatchDao.modifyPlayer(playerOfMatch);
    }

    /**
     * 更新对面球队的比分
     */
    private void updateOppositeTeamScore(long matchId, long teamId) {
        long oppositeTeamId = TeamScoreUtil.getOppositeTeamId(getMatchDao(), matchId, teamId);
        TeamScoreUtil.updateTeamScore(getTeamOfMatchDao(), getPlayerOfMatchDao(), oppositeTeamId, teamId);
    }
}
