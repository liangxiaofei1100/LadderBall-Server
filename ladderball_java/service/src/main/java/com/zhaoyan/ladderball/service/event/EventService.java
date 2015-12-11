package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    @Qualifier("hibernateEventOfMatchDao")
    EventOfMatchDao eventOfMatchDao;

    @Autowired
    @Qualifier("hibernateMatchPartDao")
    MatchPartDao matchPartDao;

    private static BeanCopier copierEventCollectionRequestToEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, EventOfMatch.class, false);

    /**
     * 添加一个事件
     */
    public EventCollectionResponse addEvent(EventCollectionRequest request) {
        EventCollectionResponse response = new EventCollectionResponse();

        for (EventCollectionRequest.Event event : request.events) {
            // 保存事件到数据库
            EventOfMatch eventOfMatch = new EventOfMatch();
            copierEventCollectionRequestToEventOfMatch.copy(event, eventOfMatch, null);
            boolean result = eventOfMatchDao.addEvent(eventOfMatch);
            if (result) {
                // 处理事件
                handleEvent(event);
            } else {
                response.buildFail("添加事件错误：event: " + event);
                return response;
            }
        }

        response.buildOk();
        return response;
    }

    private void handleEvent(EventCollectionRequest.Event event) {
        switch (event.eventCode) {
            case EventCode.EVENT_XIAO_JIE_JIE_SHU:
                // 小节结束，更新比赛小节表
                matchPartDao.setMatchPartComplete(event.matchId, event.partNumber, true);
                break;
        }
    }
}
