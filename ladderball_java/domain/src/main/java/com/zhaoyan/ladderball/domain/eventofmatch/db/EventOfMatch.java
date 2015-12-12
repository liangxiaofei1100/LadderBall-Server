package com.zhaoyan.ladderball.domain.eventofmatch.db;

import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;

import javax.persistence.*;

@Entity(name = "eventofmatch")
public class EventOfMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "event_code")
    public int eventCode;

    @Column(name = "match_id")
    public long matchId;

    @Column(name = "team_id")
    public long teamId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    public PlayerOfMatch playerOfMatch;

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
        return "EventOfMatch{" +
                "id=" + id +
                ", eventCode=" + eventCode +
                ", matchId=" + matchId +
                ", teamId=" + teamId +
                ", partNumber=" + partNumber +
                ", timeSecond=" + timeSecond +
                ", additionalData='" + additionalData + '\'' +
                ", uuid='" + uuid + '\'' +
                ", playerOfMatch=" + playerOfMatch +
                '}';
    }
}
