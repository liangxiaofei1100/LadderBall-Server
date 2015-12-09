package com.zhaoyan.ladderball.dao.player;


import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;

import java.util.List;

public interface TmpPlayerOfMatchDao {

    List<TmpPlayerOfMatch> getPlayerByTeam(long teamId);

    boolean modifyPlayer(TmpPlayerOfMatch newPlayer);
}
