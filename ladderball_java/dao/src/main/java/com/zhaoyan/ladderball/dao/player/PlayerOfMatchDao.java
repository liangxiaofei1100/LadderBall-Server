package com.zhaoyan.ladderball.dao.player;


import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;

import java.util.List;

public interface PlayerOfMatchDao {

    /**
     * 获取球队里面的所有球员
     */
    List<PlayerOfMatch> getPlayerByTeam(long teamId);

    /**
     * 根据id获取球员
     */
    PlayerOfMatch getPlayerById(long playerOfMatchId);

    /**
     * 修改球员
     */
    void modifyPlayer(PlayerOfMatch newPlayer);

    /**
     * 添加球员
     */
    void addPlayer(PlayerOfMatch player);

    /**
     * 球员球队中是否已经有了这个号码
     */
    boolean isPlayerNumberRepeated(PlayerOfMatch player);

    /**
     * 删除球员
     */
    void deletePlayer(PlayerOfMatch player);
}
