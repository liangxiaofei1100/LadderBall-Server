package com.zhaoyan.ladderball.domain.eventofmatch.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tmpeventofmatch")
public class TmpEventOfMatch {

    @Id
    public long id;
    @Column(name = "event_code")
    public int eventCode;
    @Column(name = "match_id")
    public long matchId;
    @Column(name = "team_id")
    public long teamId;
    @Column(name = "player_id")
    public long playerId;
    @Column(name = "part_number")
    public int partNumber;
    @Column(name = "time_second")
    public long timeSecond;
    @Column(name = "additional_data")
    public String additionalData;
    @Column(name = "uuid")
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
        return "TmpEventOfMatch{" +
                "id=" + id +
                ", eventCode=" + eventCode +
                ", teamId=" + matchId +
                ", teamId=" + teamId +
                ", playerId=" + playerId +
                ", partNumber=" + partNumber +
                ", timeSecond=" + timeSecond +
                ", additionalData='" + additionalData + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
