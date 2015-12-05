package com.zhaoyan.ladderball.domain.recordermatch.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "recorder_match")
public class RecorderMatch {
    @Id
    public long id;
    @Column(name = "recorder_id")
    public long recorderId;
    @Column(name = "match_id")
    public long matchId;
    @Column(name = "asigned_team")
    public int asignedTeam;

    public static final int ASIGNED_TEAM_HOME = 1;
    public static final int ASIGNED_TEAM_VISITOR = 2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(long recorderId) {
        this.recorderId = recorderId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public int getAsignedTeam() {
        return asignedTeam;
    }

    public void setAsignedTeam(int asignedTeam) {
        this.asignedTeam = asignedTeam;
    }

    @Override
    public String toString() {
        return "RecorderMatch{" +
                "id=" + id +
                ", recorderId=" + recorderId +
                ", matchId=" + matchId +
                ", asignedTeam=" + asignedTeam +
                '}';
    }
}
