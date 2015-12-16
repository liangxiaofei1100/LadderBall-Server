package com.zhaoyan.ladderball.dao.teamofmatch;


import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TmpTeamOfMatch;

public interface TmpTeamOfMatchDao {

    /**
     * 根据id获得队伍
     */
    TmpTeamOfMatch getTeamOfMatch(long tmpTeamOfMatchId);

    /**
     * 根据名字新建一个队伍
     */
    TmpTeamOfMatch addTeamOfMatch(TmpTeamOfMatch team);

    /**
     * 修改球队
     */
    void modifyTeamOfMatch(TmpTeamOfMatch team);

    /**
     * 删除一个球队
     */
    void deleteTeamOfMatch(TmpTeamOfMatch team);
}
