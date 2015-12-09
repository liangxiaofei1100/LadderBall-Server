package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class AddTmpMatchRequest extends Request {
    /**
     * 主队名称
     */
    public String teamHomeName;
    /**
     * 客队名称
     */
    public String teamVisitorName;
    /**
     * 赛制人数
     */
    public int playerNumber;

    @Override
    public String toString() {
        return super.toString() + "AddTmpMatchRequest{" +
                "teamHomeName='" + teamHomeName + '\'' +
                ", teamVisitorName='" + teamVisitorName + '\'' +
                ", playerNumber=" + playerNumber +
                '}';
    }
}
