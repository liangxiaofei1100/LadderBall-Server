package com.zhaoyan.ladderball.dao.eventofmatch;

import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateTmpEventOfMatchDao implements TmpEventOfMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;


    @Override
    public void addEvent(TmpEventOfMatch eventOfMatch) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpEventOfMatch.class);
        criteria.add(Restrictions.eq("uuid", eventOfMatch.uuid));
        List<?> events = hibernateTemplate.findByCriteria(criteria);

        if(events.isEmpty()) {
            hibernateTemplate.save(eventOfMatch);
            hibernateTemplate.flush();
            hibernateTemplate.clear();
        }
    }
}
