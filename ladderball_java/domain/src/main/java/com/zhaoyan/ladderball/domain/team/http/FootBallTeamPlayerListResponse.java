package com.zhaoyan.ladderball.domain.team.http;


import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;

public class FootBallTeamPlayerListResponse extends Response {
    public List<Player> players;

    public static class Player {
        public String id;
        public String name;
        public String photo;
        public int number;
        public String position;
    }
}
