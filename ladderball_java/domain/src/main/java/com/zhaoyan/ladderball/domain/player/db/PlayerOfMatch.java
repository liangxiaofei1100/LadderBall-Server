package com.zhaoyan.ladderball.domain.player.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "playerofmatch")
public class PlayerOfMatch {
    @Id
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "number")
    public int number;
    @Column(name = "teamofmatch_id")
    public long matchId;
    @Column(name = "is_first")
    public boolean isFirst;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }
}
