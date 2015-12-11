package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;

public class MatchAddPlayerResponse extends Response{

    public List<Player> players;

    public static class Player {
        public long id;
        public String name;
        public int number;
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

        @Override
        public String toString() {
            return "Player{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", number=" + number +
                    ", isFirst=" + isFirst +
                    '}';
        }
    }
}
