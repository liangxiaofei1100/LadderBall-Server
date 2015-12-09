package com.zhaoyan.ladderball.dao.teamofmatch;


import com.zhaoyan.ladderball.domain.teamofmatch.db.TmpTeamOfMatch;

public interface TmpTeamOfMatchDao {

    /**
     * 根据id获得队伍
     */
    TmpTeamOfMatch getTmpTeamOfMatch(long tmpTeamOfMatchId);

    /**
     * 根据名字新建一个队伍
     */
    TmpTeamOfMatch addTmpTeamOfMatch(TmpTeamOfMatch team);
}
