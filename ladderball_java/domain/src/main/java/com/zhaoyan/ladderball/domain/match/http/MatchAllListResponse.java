package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;

/**
 * 所有比赛列表，供管理平台使用
 */
public class MatchAllListResponse extends Response {

    public List<Match> matches;

    /**
     * 比赛
     */
    public static class Match {
        public long id;
        /**
         * 主队
         */
        public Team teamHome;
        /**
         * 客队
         */
        public Team teamVisitor;
        /**
         * 主队记录员
         */
        public Recorder recorderHome;
        /**
         * 客队记录员
         */
        public Recorder recorderVisitor;
        /**
         * 多少人赛制
         */
        public int playerNumber;
        /**
         * 开始时间
         */
        public long startTime;
        /**
         * 地点
         */
        public String address;
        /**
         * 是否完成
         */
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

        public boolean getComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
    }

    /**
     * 球队
     */
    public static class Team {
        public long id;
        /**
         * 队名
         */
        public String name;
        /**
         * 颜色
         */
        public String color;
        /**
         * 比分
         */
        public int score;
        /**
         * 队标
         */
        public String logoURL;

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

    /**
     * 记录员
     */
    public static class Recorder {
        public long id;
        public String phone;
        public String password;
        public String name;
        public String address;
        public int gender;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
