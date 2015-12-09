package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;

public class TmpMatchListResponse extends Response{

    public List<Match> matches;

    public static class Match {
        public long id;
        public Team teamHome;
        public Team teamVisitor;
        public int playerNumber;
        public long startTime;
        public String address;
        public boolean complete;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Team getTeamHome() {
            return teamHome;
        }

        public void setTeamHome(Team teamHome) {
            this.teamHome = teamHome;
        }

        public Team getTeamVisitor() {
            return teamVisitor;
        }

        public void setTeamVisitor(Team teamVisitor) {
            this.teamVisitor = teamVisitor;
        }

        public int getPlayerNumber() {
            return playerNumber;
        }

        public void setPlayerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
    }

    public static class Team {
        public String name;
        public String color;
        public int score;
        public String logoURL;
        public boolean isAsigned;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getLogoURL() {
            return logoURL;
        }

        public void setLogoURL(String logoURL) {
            this.logoURL = logoURL;
        }
    }
}
