package com.zhaoyan.ladderball.domain.team.http;

import com.zhaoyan.ladderball.domain.common.http.Request;


public class FootTeamInfoRequest extends Request{
    public String weiXinId;
    public long teamId;

    @Override
    public String toString() {
        return super.toString() + "FootTeamInfoRequest{" +
                "weiXinId='" + weiXinId + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
