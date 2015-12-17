package com.zhaoyan.ladderball.dao.team;

import com.zhaoyan.ladderball.domain.team.db.FootballTeam;

import java.util.List;

public interface FootballTeamDao {

    List<FootballTeam> getFootballTeamAll();

    FootballTeam getFootballTeamById(long id);

    FootballTeam getFootballTeamByName(String name);

    void addFootballTeam(FootballTeam team);

    void modifyFootballTeam(FootballTeam team);

    void deleteFootballTeam(FootballTeam team);
}
