package com.zhaoyan.ladderball.domain.match.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class TmpMatchDetailRequest extends Request{
    public long matchId;

    @Override
    public String toString() {
        return super.toString() + "MatchDetailRequest{" +
                "teamId=" + matchId +
                '}';
    }
}
