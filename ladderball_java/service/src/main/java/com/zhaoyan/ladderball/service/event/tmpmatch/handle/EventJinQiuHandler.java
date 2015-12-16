package com.zhaoyan.ladderball.service.event.tmpmatch.handle;

import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;

public class EventJinQiuHandler extends EventHandler {

    @Override
    public boolean handleAddEvent(TmpEventOfMatch event) {
        // 更新球员数据
        updatePlayerOfMatch(event.eventCode, event.playerOfMatch.id);
        // 更新本球队得分
        updateTeamScore(event.matchId, event.teamId);
        return true;
    }

    @Override
    public boolean handleDeleteEvent(TmpEventOfMatch event) {
        // 更新球员数据
        updatePlayerOfMatch(event.eventCode, event.playerOfMatch.id);
        // 更新本球队得分
        updateTeamScore(event.matchId, event.teamId);
        return true;
    }

    /**
     * 更新球员数据
     */
    private void updatePlayerOfMatch(int eventCode, long playerId) {
        // 获取进球总个数
        TmpEventOfMatchDao eventOfMatchDao = getEventOfMatchDao();
        int jinQiuCount = eventOfMatchDao.getEventCountByPlayer(eventCode, playerId);
        // 更新个人进球个数
        TmpPlayerOfMatchDao playerOfMatchDao = getPlayerOfMatchDao();
        TmpPlayerOfMatch playerOfMatch = playerOfMatchDao.getPlayerById(playerId);
        playerOfMatch.event10001 = jinQiuCount;
        playerOfMatchDao.modifyPlayer(playerOfMatch);
    }

    /**
     * 更新球队比分数据
     */
    private void updateTeamScore(long matchId, long teamId) {
        long oppositeTeamId = TeamScoreUtil.getOppositeTeamId(getMatchDao(), matchId, teamId);
        TeamScoreUtil.updateTeamScore(getTeamOfMatchDao(), getPlayerOfMatchDao(), teamId, oppositeTeamId);
    }
}