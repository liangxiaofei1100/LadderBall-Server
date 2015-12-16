package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.domain.eventofmatch.http.*;
import com.zhaoyan.ladderball.service.event.tmpmatch.handle.TmpEventHandlerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TmpEventService {
    Logger logger = LoggerFactory.getLogger(TmpEventService.class);

    @Autowired
    @Qualifier("hibernateTmpEventOfMatchDao")
    TmpEventOfMatchDao tmpEventOfMatchDao;

    @Autowired
    @Qualifier("hibernateTmpPlayerOfMatchDao")
    TmpPlayerOfMatchDao tmpPlayerOfMatchDao;

    @Autowired
    TmpEventHandlerManager tmpEventHandlerManager;

    private static BeanCopier copierEventCollectionRequestToTmpEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, TmpEventOfMatch.class, false);
    // 事件列表
    private static BeanCopier copierTmpEventOfMatchToEventPartListResponse =
            BeanCopier.create(TmpEventOfMatch.class, EventPartListResponse.Event.class, false);
    // 修改事件
    private static BeanCopier copierEventModifyRequestToTmpEventOfMatch =
            BeanCopier.create(EventModifyRequest.class, TmpEventOfMatch.class, false);

    /**
     * 添加一个事件
     */
    public EventCollectionResponse addEvent(EventCollectionRequest request) {
        EventCollectionResponse response = new EventCollectionResponse();

        for (EventCollectionRequest.Event event : request.events) {
            if (tmpEventOfMatchDao.isEventUUIDRepeated(event.uuid)) {
                // uuid重复
                response.buildFail("添加事件错误，uuid重复");
                return response;
            } else {
                // 保存事件到数据库
                TmpEventOfMatch eventOfMatch = new TmpEventOfMatch();
                copierEventCollectionRequestToTmpEventOfMatch.copy(event, eventOfMatch, null);
                eventOfMatch.playerOfMatch = tmpPlayerOfMatchDao.getPlayerById(event.playerId);
                // 处理小节结束事件，此事件每小节只有一个。如果已经有了小节结束事件，再添加时，先删除原来的小节结束事件。
                if (EventCode.EVENT_XIAO_JIE_JIE_SHU == event.eventCode) {
                    tmpEventOfMatchDao.deleteXiaoJieJieShuEvent(event.matchId, event.teamId, event.partNumber);
                }
                tmpEventOfMatchDao.addEvent(eventOfMatch);

                boolean result = tmpEventHandlerManager.handleAddEvent(eventOfMatch);
                if (!result) {
                    logger.warn("Handle add event fail. event: " + eventOfMatch);
                }
            }
        }

        response.buildOk();
        return response;
    }

    /**
     * 获取小节事件
     */
    public EventPartListResponse getPartEvent(EventPartListRequest request) {
        EventPartListResponse response = new EventPartListResponse();
        response.events = new ArrayList<>();

        List<TmpEventOfMatch> eventOfMatches = tmpEventOfMatchDao.getEventPartList(request.matchId, request.teamId, request.partNumber);

        for (TmpEventOfMatch eventOfMatch : eventOfMatches) {
            EventPartListResponse.Event event = new EventPartListResponse.Event();
            copierTmpEventOfMatchToEventPartListResponse.copy(eventOfMatch, event, null);
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

    /**
     * 删除事件
     */
    public EventDeleteResponse deleteEvent(EventDeleteRequest request) {
        EventDeleteResponse response = new EventDeleteResponse();
        TmpEventOfMatch eventOfMatch = tmpEventOfMatchDao.getEventBy(request.eventId);

        if (eventOfMatch != null) {
            tmpEventOfMatchDao.deleteEvent(eventOfMatch);
            boolean handleResult = tmpEventHandlerManager.handleDeleteEvent(eventOfMatch);
            if (!handleResult) {
                logger.warn("Handle delete event fail. " + eventOfMatch);
            }
            response.buildOk();
        } else {
            response.buildFail("事件不存在，id = " + request.eventId);
        }

        return response;
    }

    public EventModifyResponse modifyEvent(EventModifyRequest request) {
        EventModifyResponse response = new EventModifyResponse();

        TmpEventOfMatch originalEventOfMatch = tmpEventOfMatchDao.getEventBy(request.id);

        if (originalEventOfMatch != null) {
            // 更新事件并更新事件的统计数据
            TmpEventOfMatch newEventOfMatch = originalEventOfMatch;
            // clone对象，因为update之后，原来的对象也被改变了。
            originalEventOfMatch = (TmpEventOfMatch) originalEventOfMatch.clone();
            copierEventModifyRequestToTmpEventOfMatch.copy(request, newEventOfMatch, null);
            newEventOfMatch.playerOfMatch = tmpPlayerOfMatchDao.getPlayerById(request.playerId);
            tmpEventOfMatchDao.updateEvent(newEventOfMatch);

            // 处理事件
            boolean handleResult = tmpEventHandlerManager.handleModifyEvent(originalEventOfMatch, newEventOfMatch);
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
}
