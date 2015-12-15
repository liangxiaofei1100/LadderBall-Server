package com.zhaoyan.ladderball.dao.player;

import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateTmpPlayerOfMatchDao implements TmpPlayerOfMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<TmpPlayerOfMatch> getPlayerByTeam(long teamId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpPlayerOfMatch.class);
        criteria.add(Restrictions.eq("teamId", teamId));
        List<TmpPlayerOfMatch> playerOfMatches = (List<TmpPlayerOfMatch>) hibernateTemplate.findByCriteria(criteria);
        return playerOfMatches;
    }

    @Override
    public TmpPlayerOfMatch getPlayerById(long playerOfMatchId) {
        return hibernateTemplate.get(TmpPlayerOfMatch.class, playerOfMatchId);
    }

    @Override
    public void modifyPlayer(TmpPlayerOfMatch player) {
        hibernateTemplate.update(player);
    }

    @Override
    public void addPlayer(TmpPlayerOfMatch player) {
        hibernateTemplate.save(player);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public boolean isPlayerNumberRepeated(TmpPlayerOfMatch player) {
        // 查询同一个球队相同号码的球员
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpPlayerOfMatch.class);
        criteria.add(Restrictions.eq("teamId", player.teamId));
        criteria.add(Restrictions.eq("number", player.number));
        List<TmpPlayerOfMatch> playerOfMatches = (List<TmpPlayerOfMatch>) hibernateTemplate.findByCriteria(criteria);
        return !playerOfMatches.isEmpty();
    }
}
