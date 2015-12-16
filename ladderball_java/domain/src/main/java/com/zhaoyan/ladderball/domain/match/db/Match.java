package com.zhaoyan.ladderball.domain.match.db;


import javax.persistence.*;
import java.util.Date;

/**
 * 足球比赛表
 */
@Entity(name = "footballmatch")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "team_home")
    public long teamHome;
    @Column(name = "team_visitor")
    public long teamVisitor;
    @Column(name = "player_number")
    public int playerNumber;
    @Column(name = "start_time")
    public Date startTime;
    @Column(name = "address")
    public String address;
    @Column(name = "total_part")
    public int totalPart;
    @Column(name = "part_minutes")
    public int partMinutes;
    @Column(name = "complete")
    public boolean complete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(long teamHome) {
        this.teamHome = teamHome;
    }

    public long getTeamVisitor() {
        return teamVisitor;
    }

    public void setTeamVisitor(long teamVisitor) {
        this.teamVisitor = teamVisitor;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPart() {
        return totalPart;
    }

    public void setTotalPart(int totalPart) {
        this.totalPart = totalPart;
    }

    public int getPartMinutes() {
        return partMinutes;
    }

    public void setPartMinutes(int partMinutes) {
        this.partMinutes = partMinutes;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", teamHome=" + teamHome +
                ", teamVisitor=" + teamVisitor +
                ", playerNumber=" + playerNumber +
                ", startTime=" + startTime +
                ", address='" + address + '\'' +
                ", totalPart=" + totalPart +
                ", partMinutes=" + partMinutes +
                ", complete=" + complete +
                '}';
    }
}
