package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

@Service
public class TmpEventService {

    @Autowired
    @Qualifier("hibernateTmpEventOfMatchDao")
    TmpEventOfMatchDao tmpEventOfMatchDao;

    private static BeanCopier copierEventCollectionRequestToTmpEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, TmpEventOfMatch.class, false);

    /**
     * 添加一个事件
     */
    public EventCollectionResponse addEvent(EventCollectionRequest request) {
        EventCollectionResponse response = new EventCollectionResponse();

        for (EventCollectionRequest.Event event : request.events) {
            TmpEventOfMatch eventOfMatch = new TmpEventOfMatch();
            copierEventCollectionRequestToTmpEventOfMatch.copy(event, eventOfMatch, null);
            tmpEventOfMatchDao.addEvent(eventOfMatch);
        }

        response.buildOk();
        return response;
    }
}
