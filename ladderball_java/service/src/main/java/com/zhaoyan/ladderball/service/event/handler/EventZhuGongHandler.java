package com.zhaoyan.ladderball.service.event.handler;


import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;

public class EventZhuGongHandler extends EventHandler{

    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        return false;
    }

}
