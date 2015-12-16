package com.zhaoyan.ladderball.service.event.tmpmatch.handle;

import com.zhaoyan.ladderball.dao.match.TmpMatchPartDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.domain.match.db.TmpMatchPart;

public class EventXiaoJieJieShuHandler extends EventHandler {

    @Override
    public boolean handleAddEvent(TmpEventOfMatch event) {
        logger.debug("handleEvent() event: " + event);
        TmpMatchPartDao matchPartDao = getMatchPartDao();
        TmpMatchPart matchPart = matchPartDao.getMatchPartByMatchIdPartNumber(event.matchId, event.partNumber);
        if (matchPart != null) {
            matchPart.isComplete = true;
            matchPartDao.modifyMatchPart(matchPart);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean handleDeleteEvent(TmpEventOfMatch event) {
        // 暂时不需要处理
        return true;
    }
}
