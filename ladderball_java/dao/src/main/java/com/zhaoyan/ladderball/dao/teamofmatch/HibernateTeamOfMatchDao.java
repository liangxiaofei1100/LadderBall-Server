package com.zhaoyan.ladderball.dao.teamofmatch;

import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTeamOfMatchDao implements TeamOfMatchDao {
    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public TeamOfMatch getTeamOfMatch(long teamOfMatchId) {
        return hibernateTemplate.get(TeamOfMatch.class, teamOfMatchId);
    }

    @Override
    public void addTeamOfMatch(TeamOfMatch teamOfMatch) {
        hibernateTemplate.save(teamOfMatch);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyTeamOfMatch(TeamOfMatch teamOfMatch) {
        hibernateTemplate.update(teamOfMatch);
    }

    @Override
    public void deleteTeamOfMatch(TeamOfMatch teamOfMatch) {
        hibernateTemplate.delete(teamOfMatch);
    }
}
