package com.zhaoyan.ladderball.dao.player;


import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;

import java.util.List;

public interface PlayerOfMatchDao {

    List<PlayerOfMatch> getPlayerByTeam(long matchId);
}
