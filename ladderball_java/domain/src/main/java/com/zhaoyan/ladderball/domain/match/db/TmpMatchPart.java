package com.zhaoyan.ladderball.domain.match.db;

import javax.persistence.*;

/**
 * 比赛小节
 */
@Entity(name = "tmpmatch_part")
public class TmpMatchPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "number")
    public int partNumber;
    @Column(name = "match_id")
    public long matchId;
    @Column(name = "is_complete")
    public boolean isComplete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "MatchPart{" +
                "id=" + id +
                ", partNumber=" + partNumber +
                ", matchId=" + matchId +
                ", isComplete=" + isComplete +
                '}';
    }
}
