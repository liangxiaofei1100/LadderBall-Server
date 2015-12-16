package com.zhaoyan.ladderball.service.event.tmpmatch.handle;

import com.zhaoyan.ladderball.dao.match.TmpMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TmpTeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.TmpMatch;
import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TmpTeamOfMatch;

import java.util.List;

/**
 * 练习赛
 */
public class TeamScoreUtil {
    /**
     * 获取对面球
     */
    public static long getOppositeTeamId(TmpMatchDao matchDao, long matchId, long teamId) {
        long oppositeTeamId;
        TmpMatch match = matchDao.getMatch(matchId);
        if (match.teamHome == teamId) {
            oppositeTeamId = match.teamVisitor;
        } else {
            oppositeTeamId = match.teamHome;
        }
        return oppositeTeamId;
    }

    /**
     * 更新队伍得分
     */
    public static void updateTeamScore(TmpTeamOfMatchDao teamOfMatchDao, TmpPlayerOfMatchDao playerOfMatchDao, long teamId, long oppositeTeamId) {
        TmpTeamOfMatch teamOfMatch = teamOfMatchDao.getTeamOfMatch(teamId);
        teamOfMatch.score = calculateTeamScore(playerOfMatchDao, teamId, oppositeTeamId);
        teamOfMatchDao.modifyTeamOfMatch(teamOfMatch);
    }

    /**
     * 计算一个队伍的得分。规则为本队球员进球总数 + 对面球队乌龙总数。
     *
     * @param playerOfMatchDao PlayerOfMatchDao
     * @param teamId           本队id
     * @return 本队得分
     */
    public static int calculateTeamScore(TmpPlayerOfMatchDao playerOfMatchDao, long teamId, long oppositeTeamId) {
        // 本队进球
        int jinqiuCount = 0;
        List<TmpPlayerOfMatch> playerOfTeam = playerOfMatchDao.getPlayerByTeam(teamId);
        for (TmpPlayerOfMatch p : playerOfTeam) {
            jinqiuCount += p.event10001;
        }
        // 对面乌龙
        int wulongCount = 0;
        List<TmpPlayerOfMatch> playerOfOppisiteTeam = playerOfMatchDao.getPlayerByTeam(oppositeTeamId);
        for (TmpPlayerOfMatch p : playerOfOppisiteTeam) {
            wulongCount += p.event10027;
        }
        return jinqiuCount + wulongCount;
    }
}
