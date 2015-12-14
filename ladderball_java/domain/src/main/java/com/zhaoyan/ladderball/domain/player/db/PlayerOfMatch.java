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

    @Column(name = "event10003")
    public int event10003;

    @Column(name = "event10004")
    public int event10004;

    @Column(name = "event10005")
    public int event10005;

    @Column(name = "event10006")
    public int event10006;

    @Column(name = "event10007")
    public int event10007;

    @Column(name = "event10008")
    public int event10008;

    @Column(name = "event10009")
    public int event10009;

    @Column(name = "event10010")
    public int event10010;

    @Column(name = "event10011")
    public int event10011;

    @Column(name = "event10012")
    public int event10012;

    @Column(name = "event10013")
    public int event10013;

    @Column(name = "event10014")
    public int event10014;

    @Column(name = "event10015")
    public int event10015;

    @Column(name = "event10016")
    public int event10016;

    @Column(name = "event10017")
    public int event10017;

    @Column(name = "event10018")
    public int event10018;

    @Column(name = "event10019")
    public int event10019;

    @Column(name = "event10020")
    public int event10020;

    @Column(name = "event10021")
    public int event10021;

    @Column(name = "event10022")
    public int event10022;

    @Column(name = "event10023")
    public int event10023;

    @Column(name = "event10024")
    public int event10024;

    @Column(name = "event10025")
    public int event10025;

    @Column(name = "event10026")
    public int event10026;

    @Column(name = "event10027")
    public int event10027;

    @Column(name = "event10028")
    public int event10028;

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
