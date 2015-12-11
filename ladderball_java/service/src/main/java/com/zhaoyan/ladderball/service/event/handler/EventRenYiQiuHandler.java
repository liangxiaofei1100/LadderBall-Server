package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;

/**
 * Created by liangxiaofei on 15/12/11.
 */
public class EventRenYiQiuHandler extends EventHandler{
    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        return false;
    }
}
