package com.zhaoyan.ladderball.dao.teamofmatch;


import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;

public interface TeamOfMatchDao {
    /**
     * 根据id获取球队
     */
    TeamOfMatch getTeamOfMatch(long teamOfMatchId);

    /**
     * 修改球队
     */
    boolean updateTeamOfMatch(TeamOfMatch teamOfMatch);
}
