package com.zhaoyan.ladderball.domain.match.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class MatchAsignDeleteRequest extends Request{
    public long matchId;
    public int team;

    public static final int TEAM_HOME = 1;
    public static final int TEAM_VISITOR = 2;
}
