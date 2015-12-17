package com.zhaoyan.ladderball.dao.recordermatch;


import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;

import java.util.List;

public interface RecorderMatchDao {

    /**
     * 获取记录员的所有比赛列表
     */
    List<RecorderMatch> getRecorderMatchByRecorder(long recorderId);

    /**
     * 根据比赛是否完成，获取记录员的比赛列表
     */
    List<RecorderMatch> getRecorderMatchByRecorder(long recorderId, boolean isComplete);

    /**
     * 根据记录员和比赛获取一条比赛分配信息。
     */
    RecorderMatch getRecorderMatchByRecorderMatch(long recorderId, long matchId);

    /**
     * 根据比赛id和分配的队伍查询
     */
    RecorderMatch getRecorderMatchByMatchIdAsignedTeam(long matchId, int asignedTeam);

    /**
     * 根据比赛id获取比赛的记录员
     */
    List<RecorderMatch> getRecordByMatchId(long matchId);

    /**
     * 根据id获取
     */
    RecorderMatch getRecorderMatchById(long id);

    /**
     * 添加
     */
    void addRecorderMatch(RecorderMatch recorderMatch);

    /**
     * 修改
     */
    void modifyRecorderMatch(RecorderMatch recorderMatch);

    /**
     * 删除
     */
    void deleteRecorderMatch(RecorderMatch recorderMatch);
}
