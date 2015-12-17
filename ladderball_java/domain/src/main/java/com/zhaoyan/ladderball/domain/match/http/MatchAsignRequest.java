package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

/**
 * 分配比赛给记录员
 */
public class MatchAsignRequest extends Request{

    /**
     * 比赛id
     */
    public long matchId;

    /**
     * 记录员电话号码
     */
    public String recorderPhone;

    /**
     * 分配队伍
     */
    public int asignedTeam;

    public static final int ASIGNED_TEAM_HOME = 1;
    public static final int ASIGNED_TEAM_VISITOR = 2;

    @Override
    public String toString() {
        return super.toString() + "MatchAsignRequest{" +
                "matchId=" + matchId +
                ", recorderPhone='" + recorderPhone + '\'' +
                ", asignedTeam=" + asignedTeam +
                '}';
    }
}
