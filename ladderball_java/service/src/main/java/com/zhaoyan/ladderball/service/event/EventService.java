package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import com.zhaoyan.ladderball.domain.eventofmatch.http.*;
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

    private static BeanCopier copierEventModifyRequestToEventOfMatch =
            BeanCopier.create(EventModifyRequest.class, EventOfMatch.class, false);

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
                EventOfMatch eventOfMatch = saveEvent(event);
                // 处理事件
                boolean handleResult = handleAddEvent(eventOfMatch);
                if (!handleResult) {
                    logger.warn("Handle add event fail. " + event);
                }
            }
        }

        response.buildOk();
        return response;
    }

    /**
     * 保存事件到数据库
     */
    private EventOfMatch saveEvent(EventCollectionRequest.Event event) {
        EventOfMatch eventOfMatch = new EventOfMatch();
        copierEventCollectionRequestToEventOfMatch.copy(event, eventOfMatch, null);
        eventOfMatch.playerOfMatch = playerOfMatchDao.getPlayerByPlayerOfMatchId(event.playerId);
        // 处理小节结束事件，此事件每小节只有一个。如果已经有了小节结束事件，再添加时，先删除原来的小节结束事件。
        if (EventCode.EVENT_XIAO_JIE_JIE_SHU == event.eventCode) {
            eventOfMatchDao.deleteXiaoJieJieShuEvent(event.matchId, event.teamId, event.partNumber);
        }
        eventOfMatchDao.addEvent(eventOfMatch);
        return eventOfMatch;
    }

    private boolean handleAddEvent(EventOfMatch eventOfMatch) {
        return eventHandlerManager.handleAddEvent(eventOfMatch);
    }

    /**
     * 删除事件
     */
    public EventDeleteResponse deleteEvent(EventDeleteRequest request) {
        EventDeleteResponse response = new EventDeleteResponse();
        EventOfMatch eventOfMatch = eventOfMatchDao.getEventBy(request.eventId);

        if (eventOfMatch != null) {
            eventOfMatchDao.deleteEvent(eventOfMatch);
            boolean handleResult = handleDeleteEvent(eventOfMatch);
            if (!handleResult) {
                logger.warn("Handle delete event fail. " + eventOfMatch);
            }
            response.buildOk();
        } else {
            response.buildFail("事件不存在，id = " + request.eventId);
        }

        return response;
    }

    private boolean handleDeleteEvent(EventOfMatch eventOfMatch) {
        return eventHandlerManager.handleDeleteEvent(eventOfMatch);
    }

    /**
     * 修改事件
     */
    public EventModifyResponse modifyEvent(EventModifyRequest request) {
        EventModifyResponse response = new EventModifyResponse();

        EventOfMatch originalEventOfMatch = eventOfMatchDao.getEventBy(request.id);

        if (originalEventOfMatch != null) {
            // 更新事件并更新事件的统计数据
            EventOfMatch newEventOfMatch = originalEventOfMatch;
            // clone对象，因为update之后，原来的对象也被改变了。
            originalEventOfMatch = (EventOfMatch) originalEventOfMatch.clone();
            copierEventModifyRequestToEventOfMatch.copy(request, newEventOfMatch, null);
            newEventOfMatch.playerOfMatch = playerOfMatchDao.getPlayerByPlayerOfMatchId(request.playerId);
            eventOfMatchDao.updateEvent(newEventOfMatch);

            // 处理事件
            boolean handleResult = handleModifyEvent(originalEventOfMatch, newEventOfMatch);
            if (!handleResult) {
                logger.warn("Handle modify event fail. " + newEventOfMatch);
            }

            response.buildOk();
        } else {
            response.buildFail("事件不存在，id = " + request.id);
            return response;
        }

        response.buildOk();
        return response;
    }

    private boolean handleModifyEvent(EventOfMatch originalEventOfMatch, EventOfMatch newEventOfMatch) {
        logger.debug("originalEventOfMatch: " + originalEventOfMatch);
        logger.debug("newEventOfMatch: " + newEventOfMatch);
        return eventHandlerManager.handleModifyEvent(originalEventOfMatch, newEventOfMatch);
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
