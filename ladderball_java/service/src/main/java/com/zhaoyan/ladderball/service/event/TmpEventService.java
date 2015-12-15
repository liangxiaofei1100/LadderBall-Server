package com.zhaoyan.ladderball.service.event;

import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
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

    @Autowired
    @Qualifier("hibernateTmpPlayerOfMatchDao")
    TmpPlayerOfMatchDao tmpPlayerOfMatchDao;

    private static BeanCopier copierEventCollectionRequestToTmpEventOfMatch =
            BeanCopier.create(EventCollectionRequest.Event.class, TmpEventOfMatch.class, false);

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

                // TODO 需要添加事件处理
            }
        }

        response.buildOk();
        return response;
    }
}
