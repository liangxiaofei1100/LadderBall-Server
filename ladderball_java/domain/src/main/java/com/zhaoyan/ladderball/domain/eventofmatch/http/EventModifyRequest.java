package com.zhaoyan.ladderball.domain.eventofmatch.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class EventModifyRequest extends Request{
    /**
     * 事件id
     */
    public long id;
    /**
     * 事件编号
     */
    public int eventCode;
    /**
     * 比赛id
     */
    public long matchId;
    /**
     * 球队id
     */
    public long teamId;
    /**
     * 球员id
     */
    public long playerId;
    /**
     * 节数
     */
    public int partNumber;
    /**
     * 时间戳
     */
    public long timeSecond;
    /**
     * 额外数据
     */
    public String additionalData;
    /**
     * 事件的uuid，避免重复
     */
    public String uuid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public long getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(long timeSecond) {
        this.timeSecond = timeSecond;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return super.toString() + "EventModifyRequest{" +
                "id=" + id +
                ", eventCode=" + eventCode +
                ", matchId=" + matchId +
                ", teamId=" + teamId +
                ", playerId=" + playerId +
                ", partNumber=" + partNumber +
                ", timeSecond=" + timeSecond +
                ", additionalData='" + additionalData + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
