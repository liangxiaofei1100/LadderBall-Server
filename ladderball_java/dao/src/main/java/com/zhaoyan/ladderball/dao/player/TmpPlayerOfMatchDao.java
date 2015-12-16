package com.zhaoyan.ladderball.dao.player;


import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;

import java.util.List;

public interface TmpPlayerOfMatchDao {

    /**
     * 获取球队里面的所有球员
     */
    List<TmpPlayerOfMatch> getPlayerByTeam(long teamId);

    /**
     * 根据id获取球员
     */
    TmpPlayerOfMatch getPlayerById(long playerOfMatchId);

    /**
     * 修改球员
     */
    void modifyPlayer(TmpPlayerOfMatch player);

    /**
     * 添加球员
     */
    void addPlayer(TmpPlayerOfMatch player);

    /**
     * 球员球队中是否已经有了这个号码
     */
    boolean isPlayerNumberRepeated(TmpPlayerOfMatch player);

    /**
     * 删除球员
     */
    void deletePlayer(TmpPlayerOfMatch player);
}
