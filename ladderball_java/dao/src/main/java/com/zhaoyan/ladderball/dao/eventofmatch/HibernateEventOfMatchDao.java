package com.zhaoyan.ladderball.dao.eventofmatch;

import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateEventOfMatchDao implements EventOfMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;


    @Override
    public void addEvent(EventOfMatch eventOfMatch) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EventOfMatch.class);
        criteria.add(Restrictions.eq("uuid", eventOfMatch.uuid));
        List<?> events = hibernateTemplate.findByCriteria(criteria);

        if(events.isEmpty()) {
            hibernateTemplate.save(eventOfMatch);
            hibernateTemplate.flush();
            hibernateTemplate.clear();
        }
    }
}