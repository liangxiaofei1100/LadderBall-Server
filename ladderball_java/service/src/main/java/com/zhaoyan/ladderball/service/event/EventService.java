package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionResponse;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventPartListRequest;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventPartListResponse;
import com.zhaoyan.ladderball.service.event.handler.EventHandlerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    @Qualifier("hibernateEventOfMatchDao")
    EventOfMatchDao eventOfMatchDao;

    @Autowired
    @Qualifier("hibernateMatchPartDao")
    MatchPartDao matchPartDao;

    @Autowired
    @Qualifier("hibernatePlayerOfMatchDao")
    PlayerOfMatchDao playerOfMatchDao;

    @Autowired
    EventHandlerManager eventHandlerManager;

    private static BeanCopier copierEventCollectionRequestToEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, EventOfMatch.class, false);

    private static BeanCopier copierEventOfMatchToEventPartListResponse =
            BeanCopier.create(EventOfMatch.class, EventPartListResponse.Event.class, false);

    /**
     * 添加一个事件
     */
    public EventCollectionResponse addEvent(EventCollectionRequest request) {
        EventCollectionResponse response = new EventCollectionResponse();

        for (EventCollectionRequest.Event event : request.events) {
            if (eventOfMatchDao.isEventUUIDRepeated(event.uuid)) {
                // uuid重复
                response.buildFail("添加事件错误，uuid重复");
                return response;
            } else {
                // 保存事件到数据库
                EventOfMatch eventOfMatch = new EventOfMatch();
                copierEventCollectionRequestToEventOfMatch.copy(event, eventOfMatch, null);
                eventOfMatch.playerOfMatch = playerOfMatchDao.getPlayerByPlayerOfMatchId(event.playerId);

                boolean result = eventOfMatchDao.addEvent(eventOfMatch);
                if (result) {
                    // 处理事件
                    boolean handleResult = handleEvent(event);
                    if (!handleResult) {
                        logger.warn("Handle event fail. " + event);
                    }
                } else {
                    response.buildFail("添加事件错误，事件: " + event);
                    return response;
                }
            }
        }

        response.buildOk();
        return response;
    }

    private boolean handleEvent(EventCollectionRequest.Event event) {
        return eventHandlerManager.handleEvent(event);
    }

    /**
     * 获取小节事件
     */
    public EventPartListResponse getPartEvent(EventPartListRequest request) {
        EventPartListResponse response = new EventPartListResponse();
        response.events = new ArrayList<>();

        List<EventOfMatch> eventOfMatches = eventOfMatchDao.getEventPartList(request.matchId, request.teamId, request.partNumber);

        for (EventOfMatch eventOfMatch : eventOfMatches) {
            EventPartListResponse.Event event = new EventPartListResponse.Event();
            copierEventOfMatchToEventPartListResponse.copy(eventOfMatch, event, null);
            if (eventOfMatch.playerOfMatch != null) {
                event.playerId = eventOfMatch.playerOfMatch.id;
                event.playerNumber = eventOfMatch.playerOfMatch.number;
            } else {
                event.playerId = -1;
                event.playerNumber = -1;
            }
            response.events.add(event);
        }

        response.buildOk();
        return response;
    }
}
