package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import org.springframework.stereotype.Service;

/**
 * Created by liangxiaofei on 15/12/11.
 */
@Service
public class EventJiaoQiuHandler extends EventHandler{
    @Override
    public boolean handleEvent(EventCollectionRequest.Event event) {
        return false;
    }
}
