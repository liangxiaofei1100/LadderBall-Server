package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import com.zhaoyan.ladderball.domain.match.db.MatchPart;

public class EventXiaoJieJieShuHandler extends EventHandler {

    @Override
    public boolean handleAddEvent(EventOfMatch event) {
        logger.debug("handleEvent() event: " + event);
        MatchPartDao matchPartDao = getMatchPartDao();
        MatchPart matchPart = matchPartDao.getMatchPartByMatchIdPartNumber(event.matchId, event.partNumber);
        if (matchPart != null) {
            matchPart.isComplete = true;
            matchPartDao.modifyMatchPart(matchPart);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean handleDeleteEvent(EventOfMatch event) {
        // 暂时不需要处理
        return true;
    }
}
