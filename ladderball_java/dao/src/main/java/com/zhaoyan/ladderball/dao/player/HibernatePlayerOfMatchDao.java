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
    public List<PlayerOfMatch> getPlayerByTeam(long teamId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PlayerOfMatch.class);
        criteria.add(Restrictions.eq("teamId", teamId));
        List<PlayerOfMatch> playerOfMatches = (List<PlayerOfMatch>) hibernateTemplate.findByCriteria(criteria);
        return playerOfMatches;
    }

    @Override
    public PlayerOfMatch getPlayerById(long playerOfMatchId) {
        return hibernateTemplate.get(PlayerOfMatch.class, playerOfMatchId);
    }

    @Override
    public void modifyPlayer(PlayerOfMatch newPlayer) {
        hibernateTemplate.update(newPlayer);
    }

    @Override
    public void addPlayer(PlayerOfMatch player) {
        hibernateTemplate.save(player);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public boolean isPlayerNumberRepeated(PlayerOfMatch player) {
        // 查询同一个球队相同号码的球员
        DetachedCriteria criteria = DetachedCriteria.forClass(PlayerOfMatch.class);
        criteria.add(Restrictions.eq("teamId", player.teamId));
        criteria.add(Restrictions.eq("number", player.number));
        List<PlayerOfMatch> playerOfMatches = (List<PlayerOfMatch>) hibernateTemplate.findByCriteria(criteria);
        return !playerOfMatches.isEmpty();
    }

    @Override
    public void deletePlayer(PlayerOfMatch player) {
        hibernateTemplate.delete(player);
    }
}
