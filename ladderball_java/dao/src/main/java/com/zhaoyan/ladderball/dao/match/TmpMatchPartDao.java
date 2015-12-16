package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.TmpMatchPart;

import java.util.List;

public interface TmpMatchPartDao {

    /**
     * 获取比赛小节信息
     */
    List<TmpMatchPart> getMatchParts(long matchId);

    /**
     * 根据比赛和小节号获取小节
     */
    TmpMatchPart getMatchPartByMatchIdPartNumber(long matchId, int partNumber);

    /**
     * 添加一小节
     */
    void addMatchPart(TmpMatchPart matchPart);

    /**
     * 修改小节
     */
    void modifyMatchPart(TmpMatchPart matchPart);

    /**
     * 删除一小节
     */
    void deleteMatchPart(TmpMatchPart matchPart);
}
