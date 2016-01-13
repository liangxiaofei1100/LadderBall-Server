package com.zhaoyan.ladderball.domain.team.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class FootBallTeamPlayerListRequest extends Request{
    public long teamId;

    @Override
    public String toString() {
        return super.toString() + "FootBallTeamPlayerListRequest{" +
                "teamId=" + teamId +
                '}';
    }
}
