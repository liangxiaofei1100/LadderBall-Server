package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.MatchPart;
import com.zhaoyan.ladderball.domain.match.db.TmpMatchPart;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateTmpMatchPartDao implements TmpMatchPartDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<TmpMatchPart> getMatchParts(long matchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpMatchPart.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        List<TmpMatchPart> matchParts = (List<TmpMatchPart>) hibernateTemplate.findByCriteria(criteria);
        return matchParts;
    }

    @Override
    public TmpMatchPart getMatchPartByMatchIdPartNumber(long matchId, int partNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpMatchPart.class);
        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        List<TmpMatchPart> matchParts = (List<TmpMatchPart>) hibernateTemplate.findByCriteria(criteria);
        if (!matchParts.isEmpty()) {
            return matchParts.get(0);
        }
        return null;
    }

    @Override
    public void addMatchPart(TmpMatchPart matchPart) {
        hibernateTemplate.save(matchPart);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyMatchPart(TmpMatchPart matchPart) {
        hibernateTemplate.update(matchPart);
    }

    @Override
    public void deleteMatchPart(TmpMatchPart matchPart) {
        hibernateTemplate.delete(matchPart);
    }
}
