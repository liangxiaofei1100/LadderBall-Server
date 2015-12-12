package com.zhaoyan.ladderball.domain.eventofmatch.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class EventPartListRequest extends Request{
    public long matchId;
    public long teamId;
    public int partNumber;
}
