package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.MatchPart;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateMatchPartDao implements MatchPartDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<MatchPart> getMatchParts(long matchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(MatchPart.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        List<MatchPart> matchParts = (List<MatchPart>) hibernateTemplate.findByCriteria(criteria);
        return matchParts;
    }

    @Override
    public MatchPart addMatchPart(MatchPart matchPart) {
        hibernateTemplate.save(matchPart);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return matchPart;
    }

    @Override
    public void deleteMatchPart(long matchPartId) {
        MatchPart matchPart = hibernateTemplate.get(MatchPart.class, matchPartId);
        if (matchPart != null) {
            hibernateTemplate.delete(matchPart);
        }
    }

    @Override
    public void setMatchPartComplete(long matchId, int partNumber, boolean isComplete) {
        DetachedCriteria criteria = DetachedCriteria.forClass(MatchPart.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        List<MatchPart> matchParts = (List<MatchPart>) hibernateTemplate.findByCriteria(criteria);
        if (!matchParts.isEmpty()) {
            MatchPart matchPart = matchParts.get(0);
            matchPart.isComplete = isComplete;
            hibernateTemplate.update(matchPart);
        }
    }
}
