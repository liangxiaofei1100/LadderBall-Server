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
    public boolean modifyPlayer(TmpPlayerOfMatch newPlayer) {
        TmpPlayerOfMatch player = hibernateTemplate.get(TmpPlayerOfMatch.class, newPlayer.id);
        if (player == null) {
            return false;
        }
        player.name = newPlayer.name;
        player.isFirst = newPlayer.isFirst;
        player.number = newPlayer.number;
        hibernateTemplate.save(player);
        return true;
    }
}
