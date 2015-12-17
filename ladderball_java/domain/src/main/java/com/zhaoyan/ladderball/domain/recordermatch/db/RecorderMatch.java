package com.zhaoyan.ladderball.domain.recordermatch.db;

import com.zhaoyan.ladderball.domain.match.db.Match;

import javax.persistence.*;

@Entity(name = "recorder_match")
public class RecorderMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "recorder_id")
    public long recorderId;
    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public long getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(long recorderId) {
        this.recorderId = recorderId;
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
                ", match=" + match +
                ", asignedTeam=" + asignedTeam +
                '}';
    }
}
