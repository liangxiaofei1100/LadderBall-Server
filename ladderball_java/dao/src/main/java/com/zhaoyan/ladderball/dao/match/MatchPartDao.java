package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.MatchPart;

import java.util.List;

public interface MatchPartDao {

    /**
     * 获取比赛小节信息
     */
    List<MatchPart> getMatchParts(long matchId);

    /**
     * 添加一小节
     */
    MatchPart addMatchPart(MatchPart matchPart);

    /**
     * 删除一小节
     */
    void deleteMatchPart(long matchPartId);

    /**
     * 将小节设置为已完成
     */
    void setMatchPartComplete(long matchId, int partNumber, boolean isComplete);
}
