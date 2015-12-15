package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EventHandlerManager {

    public HashMap<Integer, EventHandler> eventHandlerMap = new HashMap<Integer, EventHandler>();

    public EventHandlerManager() {
        // | 进球		| 10001		|
        eventHandlerMap.put(EventCode.EVENT_JIN_QIU, new EventJinQiuHandler());

        UpdateCountEventHandler updateCountEventHandler = new UpdateCountEventHandler();
        for (int eventCode : UpdateCountEventHandler.EVENTS) {
            eventHandlerMap.put(eventCode, updateCountEventHandler);
        }

        // | 乌龙球	| 10027		|
        eventHandlerMap.put(EventCode.EVENT_WU_LONG_QIU, new EventWuLongQiuHandler());

        // | 小节结束	| 20001		|			|
        eventHandlerMap.put(EventCode.EVENT_XIAO_JIE_JIE_SHU, new EventXiaoJieJieShuHandler());
    }

    /**
     * 处理添加事件
     *
     * @param event 事件
     * @return 如果事件被成功处理，返回true，否则返回false。
     */
    public boolean handleAddEvent(EventOfMatch event) {
        EventHandler eventHandler = eventHandlerMap.get(event.eventCode);
        return eventHandler != null && eventHandler.handleAddEvent(event);
    }

    /**
     * 处理删除事件
     *
     * @param event 事件
     * @return 如果事件被成功处理，返回true，否则返回false。
     */
    public boolean handleDeleteEvent(EventOfMatch event) {
        EventHandler eventHandler = eventHandlerMap.get(event.eventCode);
        return eventHandler != null && eventHandler.handleDeleteEvent(event);
    }

    /**
     * 处理修改事件
     */
    public boolean handleModifyEvent(EventOfMatch originalEventOfMatch, EventOfMatch newEventOfMatch) {
        EventHandler originalEventHandler = eventHandlerMap.get(originalEventOfMatch.eventCode);
        boolean originalHandleResult = originalEventHandler != null && originalEventHandler.handleDeleteEvent(originalEventOfMatch);

        EventHandler newEventHandler = eventHandlerMap.get(newEventOfMatch.eventCode);
        boolean newHandlerResult = newEventHandler != null && newEventHandler.handleDeleteEvent(newEventOfMatch);

        return originalHandleResult && newHandlerResult;
    }
}
