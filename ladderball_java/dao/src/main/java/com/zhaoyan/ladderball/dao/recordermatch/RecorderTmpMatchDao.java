package com.zhaoyan.ladderball.dao.recordermatch;

import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;

import java.util.List;

public interface RecorderTmpMatchDao {

    /**
     * 根据id获取
     */
    RecorderTmpMatch getRecorderMatchById(long id);

    /**
     * 添加
     */
    void addRecorderMatch(RecorderTmpMatch recorderMatch);

    /**
     * 修改
     */
    void modifyRecorderMatch(RecorderTmpMatch recorderMatch);

    /**
     * 删除
     */
    void deleteRecorderMatch(RecorderTmpMatch recorderMatch);

    /**
     * 根据记录者id获取记录者所有的比赛
     */
    List<RecorderTmpMatch> getRecorderTmpMatchByRecorder(long recorderId);

    /**
     * 根据记录者id和比赛id获取比赛者认领关系
     */
    RecorderTmpMatch getRecorderTmpMatchByRecorderMatch(long recorderId, long tmpMatchId);

    /**
     * 添加一个记录者和比赛的对应关系
     */
    RecorderTmpMatch addRecorderTmpMatch(RecorderTmpMatch recorderTmpMatch);

    /**
     * 获取该记录这可以领取的练习赛
     */
    List<RecorderTmpMatch> getToAsignTmpMatchByRecorder(long recorderId);

    /**
     * 记录者是否已经领取了练习赛
     */
    boolean isTmpMatchAsignedToRecorder(long recorderId, long matchId);

    /**
     * 记录者领取练习赛，只能领取客场队伍
     */
    boolean asignTmpMatchVisitor(long recorderId, long matchId);
}
