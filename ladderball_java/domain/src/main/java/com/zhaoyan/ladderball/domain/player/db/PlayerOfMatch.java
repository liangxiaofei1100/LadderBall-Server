package com.zhaoyan.ladderball.domain.player.db;

import javax.persistence.*;

@Entity(name = "playerofmatch")
public class PlayerOfMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "number")
    public int number;
    @Column(name = "team_id")
    public long teamId;
    @Column(name = "is_first")
    public boolean isFirst;
    @Column(name = "event10001")
    public int event10001;
    @Column(name = "event10002")
    public int event10002;
    @Column(name = "event10027")
    public int event10027;

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

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public String toString() {
        return "PlayerOfMatch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", teamId=" + teamId +
                ", isFirst=" + isFirst +
                ", event10001=" + event10001 +
                '}';
    }
}
