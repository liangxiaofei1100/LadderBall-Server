package com.zhaoyan.ladderball.domain.match.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class MatchDetailRequest extends Request{
    public long matchId;

    @Override
    public String toString() {
        return super.toString() + "MatchDetailRequest{" +
                "matchId=" + matchId +
                '}';
    }
}
