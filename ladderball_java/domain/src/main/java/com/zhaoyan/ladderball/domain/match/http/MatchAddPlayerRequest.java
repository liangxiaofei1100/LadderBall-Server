package com.zhaoyan.ladderball.domain.match.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

import java.util.List;

public class MatchAddPlayerRequest extends Request {
    public long matchId;
    public long teamId;
    public List<Player> players;

    public static class Player {
        public String name;
        public int number;
        public boolean isFirst;

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

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    ", isFirst=" + isFirst +
                    '}';
        }
    }

    @Override
    public String toString() {
        return super.toString() + "MatchAddPlayerRequest{" +
                "matchId=" + matchId +
                ", teamId=" + teamId +
                ", players=" + players +
                '}';
    }
}
