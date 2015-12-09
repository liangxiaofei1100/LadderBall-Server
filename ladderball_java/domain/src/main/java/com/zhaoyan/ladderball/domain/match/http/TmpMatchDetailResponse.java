package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;


public class TmpMatchDetailResponse extends Response {

    public long id;

    public long startTime;
    public String address;
    /**
     * 主队
     */
    public Team teamHome;
    /**
     * 客队
     */
    public Team teamVisitor;
    /**
     * 球员人数
     */
    public int playerNumber;
    /**
     * 比赛节数
     */
    public int totalPart;
    /**
     * 每节分钟数
     */
    public int partMinutes;

    public List<PartData> partDatas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
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

    public static class Team {
        /**
         * 队伍id
         */
        public long id;
        /**
         * 是否认领
         */
        public boolean isAsigned;
        /**
         * 队伍名称
         */
        public String name;
        /**
         * 队伍颜色
         */
        public String color;
        /**
         * 队伍logo
         */
        public String logoURL;
        /**
         * 球员
         */
        public List<Player> players;

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

        public String getLogoURL() {
            return logoURL;
        }

        public void setLogoURL(String logoURL) {
            this.logoURL = logoURL;
        }
    }

    public static class Player {
        /**
         * 球员id
         */
        public long id;
        /**
         * 球员姓名
         */
        public String name;
        /**
         * 球员号码
         */
        public int number;
        /**
         * 是否首发
         */
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

        public boolean getIsFirst() {
            return isFirst;
        }

        public void setIsFirst(boolean isFirst) {
            this.isFirst = isFirst;
        }
    }

    public static class PartData {
        /**
         * 第几小节
         */
        public int partNumber;
        /**
         * 是否完成
         */
        public boolean isComplete;
    }
}
