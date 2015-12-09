package com.zhaoyan.ladderball.domain.match.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

import java.util.List;

public class TmpMatchModifyRequest extends Request {
    /**
     * 比赛id
     */
    public long matchId;
    /**
     * 比赛人数
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

    /**
     * 球员信息
     */
    public List<Player> players;


    public static class Player {
        public long id;
        public int number;
        public boolean isFirst;
        public String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "id=" + id +
                    ", number=" + number +
                    ", isFirst=" + isFirst +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchModifyRequest{" +
                "matchId=" + matchId +
                ", playerNumber=" + playerNumber +
                ", totalPart=" + totalPart +
                ", partMinutes=" + partMinutes +
                ", players=" + players +
                '}';
    }
}
