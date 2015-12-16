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
    public MatchPart getMatchPartByMatchIdPartNumber(long matchId, int partNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(MatchPart.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        List<MatchPart> matchParts = (List<MatchPart>) hibernateTemplate.findByCriteria(criteria);
        if (!matchParts.isEmpty()) {
            return matchParts.get(0);
        }
        return null;
    }

    @Override
    public void addMatchPart(MatchPart matchPart) {
        hibernateTemplate.save(matchPart);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyMatchPart(MatchPart matchPart) {
        hibernateTemplate.update(matchPart);
    }

    @Override
    public void deleteMatchPart(MatchPart matchPart) {
        hibernateTemplate.delete(matchPart);
    }
}
