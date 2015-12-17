package com.zhaoyan.ladderball.domain.team.http;

import com.zhaoyan.ladderball.domain.common.http.Response;

import java.util.List;

public class FootballTeamListMyTeamResponse extends Response{

    public List<FootballTeam> teams;

    public static class FootballTeam {
        public long id;
        public String name;
        public String address;
        public int playerNumber;
        public String captain;
    }
}
