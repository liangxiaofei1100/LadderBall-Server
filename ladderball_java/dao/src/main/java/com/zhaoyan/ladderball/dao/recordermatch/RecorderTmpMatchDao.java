package com.zhaoyan.ladderball.dao.recordermatch;

import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;

import java.util.List;

public interface RecorderTmpMatchDao {

    List<RecorderTmpMatch> getRecorderTmpMatchByRecorder(long recorderId);

    RecorderTmpMatch getRecorderTmpMatchByRecorderMatch(long recorderId, long tmpMatchId);
}
