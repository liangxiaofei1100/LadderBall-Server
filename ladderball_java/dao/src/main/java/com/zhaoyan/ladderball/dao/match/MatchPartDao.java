package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.MatchPart;

import java.util.List;

public interface MatchPartDao {

    /**
     * 获取比赛小节信息
     */
    List<MatchPart> getMatchParts(long matchId);

    /**
     * 根据比赛和小节号获取小节
     */
    MatchPart getMatchPartByMatchIdPartNumber(long matchId, int partNumber);

    /**
     * 添加一小节
     */
    MatchPart addMatchPart(MatchPart matchPart);

    /**
     * 修改小节
     */
    void modifyMatchPart(MatchPart matchPart);

    /**
     * 删除一小节
     */
    void deleteMatchPart(MatchPart matchPart);
}
