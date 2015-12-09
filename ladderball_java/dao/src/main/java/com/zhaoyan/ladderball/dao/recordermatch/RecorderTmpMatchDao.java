package com.zhaoyan.ladderball.dao.recordermatch;

import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;

import java.util.List;

public interface RecorderTmpMatchDao {

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
}
