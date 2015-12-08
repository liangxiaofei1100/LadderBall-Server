package com.zhaoyan.ladderball.dao.player;

import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernatePlayerOfMatchDao implements PlayerOfMatchDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<PlayerOfMatch> getPlayerByTeam(long matchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PlayerOfMatch.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        List<PlayerOfMatch> playerOfMatches = (List<PlayerOfMatch>) hibernateTemplate.findByCriteria(criteria);
        return playerOfMatches;
    }

    @Override
    public boolean modifyPlayer(PlayerOfMatch newPlayer) {
        PlayerOfMatch player = hibernateTemplate.get(PlayerOfMatch.class, newPlayer.id);
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
