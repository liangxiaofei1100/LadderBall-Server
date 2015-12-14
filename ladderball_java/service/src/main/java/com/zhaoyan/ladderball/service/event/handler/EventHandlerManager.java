package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EventHandlerManager {

    public HashMap<Integer, EventHandler> eventHandlerMap = new HashMap<Integer, EventHandler>();

    public EventHandlerManager() {
        // | 进球		| 10001		|
        eventHandlerMap.put(EventCode.EVENT_JIN_QIU, new EventJinQiuHandler());
        // | 助攻		| 10002		|
        eventHandlerMap.put(EventCode.EVENT_ZHU_GONG, new EventZhuGongHandler());
        // | 角球		| 10003		|
        eventHandlerMap.put(EventCode.EVENT_JIAO_QIU, new EventJiaoQiuHandler());
        // | 任意球		| 10004		|
        eventHandlerMap.put(EventCode.EVENT_REN_YI_QIU, new EventRenYiQiuHandler());
        // | 边界球		| 10005		|
        eventHandlerMap.put(EventCode.EVENT_BIAN_JIE_QIU, new EventBianJieQiuHandler());
        // | 越位		| 10006		|
        eventHandlerMap.put(EventCode.EVENT_YUE_WEI, new EventYueWeiHandler());
        // | 失误		| 10007		|
        eventHandlerMap.put(EventCode.EVENT_SHI_WU, new EventShiWuHandler());
        // | 过人成功	| 10008		|
        eventHandlerMap.put(EventCode.EVENT_GUO_REN_CHENG_GONG, new EventGuoRenChengGongHandler());

        // | 乌龙球	| 10027		|
        eventHandlerMap.put(EventCode.EVENT_WU_LONG_QIU, new EventWuLongQiuHandler());

        // | 小节结束	| 20001		|			|
        eventHandlerMap.put(EventCode.EVENT_XIAO_JIE_JIE_SHU, new EventXiaoJieJieShuHandler());
    }

    /**
     * 处理事件
     * @param event 事件
     * @return 如果事件被成功处理，返回true，否则返回false。
     */
    public boolean handleEvent(EventCollectionRequest.Event event){
        EventHandler eventHandler = eventHandlerMap.get(event.eventCode);
        return eventHandler != null && eventHandler.handleEvent(event);
    }
}
