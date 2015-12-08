package com.zhaoyan.ladderball.dao.match;


import com.zhaoyan.ladderball.domain.match.db.Match;

import org.hibernate.criterion.DetachedCriteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateMatchDao implements MatchDao {
    Logger logger = LoggerFactory.getLogger(HibernateMatchDao.class);

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<Match> getAllMatch() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Match.class);
        List<Match> matches = (List<Match>) hibernateTemplate.findByCriteria(criteria);
        return matches;
    }

    @Override
    public Match getMatch(long id) {
        return hibernateTemplate.get(Match.class, id);
    }

    @Override
    public boolean modifyMatch(long id, int playerNumber, int totalPart, int partMinutes) {
        Match match = hibernateTemplate.get(Match.class, id);
        if (match == null) {
            return false;
        }
        match.playerNumber = playerNumber;
        match.totalPart = totalPart;
        match.partMinutes = partMinutes;

        hibernateTemplate.update(match);
        return true;
    }

}
