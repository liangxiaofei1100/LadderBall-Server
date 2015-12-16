package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

/**
 * 添加比赛
 */
public class MatchAddRequest extends Request {
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

    /**
     * 比赛开始时间
     */
    public long startTime;
    /**
     * 比赛地址
     */
    public String address;

    /**
     * 比赛总节数
     */
    public int totalPart;

    /**
     * 每节分钟数
     */
    public int partMinutes;

    public String getTeamHomeName() {
        return teamHomeName;
    }

    public void setTeamHomeName(String teamHomeName) {
        this.teamHomeName = teamHomeName;
    }

    public String getTeamVisitorName() {
        return teamVisitorName;
    }

    public void setTeamVisitorName(String teamVisitorName) {
        this.teamVisitorName = teamVisitorName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPart() {
        return totalPart;
    }

    public void setTotalPart(int totalPart) {
        this.totalPart = totalPart;
    }

    public int getPartMinutes() {
        return partMinutes;
    }

    public void setPartMinutes(int partMinutes) {
        this.partMinutes = partMinutes;
    }

    @Override
    public String toString() {
        return "MatchAddRequest{" +
                "teamHomeName='" + teamHomeName + '\'' +
                ", teamVisitorName='" + teamVisitorName + '\'' +
                ", playerNumber=" + playerNumber +
                ", startTime=" + startTime +
                ", address='" + address + '\'' +
                ", totalPart=" + totalPart +
                ", partMinutes=" + partMinutes +
                '}';
    }
}
