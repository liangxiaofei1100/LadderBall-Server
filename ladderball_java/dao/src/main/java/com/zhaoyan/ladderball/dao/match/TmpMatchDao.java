package com.zhaoyan.ladderball.dao.match;


import com.zhaoyan.ladderball.domain.match.db.TmpMatch;

import java.util.List;

public interface TmpMatchDao {
    /**
     * 查询所有比赛
     */
    List<TmpMatch> getAllMatch();

    /**
     * 查询一场比赛
     */
    TmpMatch getMatch(long id);

    /**
     * 修改一场比赛
     */
    boolean modifyMatch(long id, int playerNumber, int totalPart, int partMinutes);

    /**
     * 添加一场比赛
     */
    TmpMatch addMatch(TmpMatch match);
}
