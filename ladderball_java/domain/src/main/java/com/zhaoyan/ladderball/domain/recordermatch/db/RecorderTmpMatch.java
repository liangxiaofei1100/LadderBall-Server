package com.zhaoyan.ladderball.domain.recordermatch.db;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * 记录员对应比赛
 */
@Entity(name = "recorder_tmpmatch")
public class RecorderTmpMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "recorder_id")
    public long recorderId;
    @Column(name = "match_id")
    public long matchId;
    @Column(name = "asigned_team")
    public int asignedTeam;
    /**
     * 是否比赛两个队伍都有人认领，用于判断其他人是否可以认领该场比赛
     */
    @Column(name = "is_match_asigned")
    public boolean isMatchAsigned;

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

    public boolean getIsMatchAsigned() {
        return isMatchAsigned;
    }

    public void setIsMatchAsigned(boolean isMatchAsigned) {
        this.isMatchAsigned = isMatchAsigned;
    }

    @Override
    public String toString() {
        return "RecorderTmpMatch{" +
                "id=" + id +
                ", recorderId=" + recorderId +
                ", matchId=" + matchId +
                ", asignedTeam=" + asignedTeam +
                ", isMatchAsigned=" + isMatchAsigned +
                '}';
    }
}
