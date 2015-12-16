package com.zhaoyan.ladderball.dao.teamofmatch;


import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;

public interface TeamOfMatchDao {
    /**
     * 根据id获取球队
     */
    TeamOfMatch getTeamOfMatch(long teamOfMatchId);

    /**
     * 添加一个球队
     */
    void addTeamOfMatch(TeamOfMatch teamOfMatch);

    /**
     * 修改球队
     */
    void modifyTeamOfMatch(TeamOfMatch teamOfMatch);

    /**
     * 删除一个球队
     */
    void deleteTeamOfMatch(TeamOfMatch teamOfMatch);
}
