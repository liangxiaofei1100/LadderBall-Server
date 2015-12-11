package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;

public class EventXiaoJieJieShuHandler extends EventHandler {

    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        logger.debug("handleEvent() event: " + event);
        MatchPartDao matchPartDao = getMatchPartDao();
        matchPartDao.setMatchPartComplete(event.matchId, event.partNumber, true);
        return true;
    }
}
