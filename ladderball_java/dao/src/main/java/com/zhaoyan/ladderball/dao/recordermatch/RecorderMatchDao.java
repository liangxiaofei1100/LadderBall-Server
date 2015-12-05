package com.zhaoyan.ladderball.dao.recordermatch;


import com.zhaoyan.ladderball.domain.match.db.Match;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;

import java.util.List;

public interface RecorderMatchDao {

    List<RecorderMatch> getRecorderMatch(long recorderId);
}
