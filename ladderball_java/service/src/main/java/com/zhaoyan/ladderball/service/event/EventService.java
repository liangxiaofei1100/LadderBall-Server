package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
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

    private static BeanCopier copierEventCollectionRequestToEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, EventOfMatch.class, false);

    /**
     * 添加一个事件
     */
    public EventCollectionResponse addEvent(EventCollectionRequest request) {
        EventCollectionResponse response = new EventCollectionResponse();

        for (EventCollectionRequest.Event event : request.events) {
            EventOfMatch eventOfMatch = new EventOfMatch();
            copierEventCollectionRequestToEventOfMatch.copy(event, eventOfMatch, null);
            eventOfMatchDao.addEvent(eventOfMatch);
        }

        response.buildOk();
        return response;
    }
}
