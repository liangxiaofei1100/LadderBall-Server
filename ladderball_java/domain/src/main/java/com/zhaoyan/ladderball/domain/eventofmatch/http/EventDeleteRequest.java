package com.zhaoyan.ladderball.domain.eventofmatch.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class EventDeleteRequest extends Request {
    /**
     * 事件id
     */
    public long eventId;

    @Override
    public String toString() {
        return super.toString() + "EventDeleteRequest{" +
                "eventId=" + eventId +
                '}';
    }
}
