package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.match.MatchDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.Match;
import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;

import java.util.List;

public class TeamScoreUtil {
    /**
     * 获取对面球队id
     */
    public static long getOppositeTeamId(MatchDao matchDao, long matchId, long teamId) {
        long oppositeTeamId;
        Match match = matchDao.getMatch(matchId);
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
    public static void updateTeamScore(TeamOfMatchDao teamOfMatchDao, PlayerOfMatchDao playerOfMatchDao, long teamId, long oppositeTeamId) {
        TeamOfMatch teamOfMatch = teamOfMatchDao.getTeamOfMatch(teamId);
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
    public static int calculateTeamScore(PlayerOfMatchDao playerOfMatchDao, long teamId, long oppositeTeamId) {
        // 本队进球
        int jinqiuCount = 0;
        List<PlayerOfMatch> playerOfTeam = playerOfMatchDao.getPlayerByTeam(teamId);
        for (PlayerOfMatch p : playerOfTeam) {
            jinqiuCount += p.event10001;
        }
        // 对面乌龙
        int wulongCount = 0;
        List<PlayerOfMatch> playerOfOppisiteTeam = playerOfMatchDao.getPlayerByTeam(oppositeTeamId);
        for (PlayerOfMatch p : playerOfOppisiteTeam) {
            wulongCount += p.event10027;
        }
        return jinqiuCount + wulongCount;
    }
}
