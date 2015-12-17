package com.zhaoyan.ladderball.dao.team;

import com.zhaoyan.ladderball.domain.team.db.FootballTeam;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateFootballTeamDao implements FootballTeamDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<FootballTeam> getFootballTeamAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(FootballTeam.class);
        List<FootballTeam> teams = (List<FootballTeam>) hibernateTemplate.findByCriteria(criteria);
        return teams;
    }

    @Override
    public FootballTeam getFootballTeamById(long id) {
        return hibernateTemplate.get(FootballTeam.class, id);
    }

    @Override
    public FootballTeam getFootballTeamByName(String name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(FootballTeam.class);
        criteria.add(Restrictions.eq("name", name));

        List<FootballTeam> teams = (List<FootballTeam>) hibernateTemplate.findByCriteria(criteria);
        if (!teams.isEmpty()) {
            return teams.get(0);
        }
        return null;
    }

    @Override
    public void addFootballTeam(FootballTeam team) {
        hibernateTemplate.save(team);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyFootballTeam(FootballTeam team) {
        hibernateTemplate.update(team);
    }

    @Override
    public void deleteFootballTeam(FootballTeam team) {
        hibernateTemplate.delete(team);
    }
}
