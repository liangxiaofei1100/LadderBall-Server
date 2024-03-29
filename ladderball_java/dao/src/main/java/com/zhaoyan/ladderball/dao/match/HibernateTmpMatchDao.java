package com.zhaoyan.ladderball.dao.match;


import com.zhaoyan.ladderball.domain.match.db.TmpMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateTmpMatchDao implements TmpMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<TmpMatch> getAllMatch() {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpMatch.class);
        List<TmpMatch> matches = (List<TmpMatch>) hibernateTemplate.findByCriteria(criteria);
        return matches;
    }

    @Override
    public TmpMatch getMatch(long id) {
        return hibernateTemplate.get(TmpMatch.class, id);
    }

    @Override
    public void modifyMatch(TmpMatch match) {
        hibernateTemplate.update(match);
    }

    @Override
    public void addMatch(TmpMatch match) {
        hibernateTemplate.save(match);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void deleteMatch(TmpMatch match) {
        hibernateTemplate.delete(match);
    }
}
