package com.zhaoyan.ladderball.dao.eventofmatch;

import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateEventOfMatchDao implements EventOfMatchDao {
    Logger logger = LoggerFactory.getLogger(HibernateEventOfMatchDao.class);

    @Autowired
    HibernateTemplate hibernateTemplate;


    @Override
    public boolean isEventUUIDRepeated(String uuid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EventOfMatch.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        List<?> events = hibernateTemplate.findByCriteria(criteria);
        return !events.isEmpty();
    }

    @Override
    public boolean addEvent(EventOfMatch eventOfMatch) {
        hibernateTemplate.save(eventOfMatch);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return true;
    }

    @Override
    public void updateEvent(EventOfMatch eventOfMatch) {
        hibernateTemplate.update(eventOfMatch);
    }

    @Override
    public int getEventCountByPlayer(int eventCode, long playerId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EventOfMatch.class);
        criteria.add(Restrictions.eq("eventCode", eventCode));
        criteria.add(Restrictions.eq("playerOfMatch.id", playerId));
        List<?> events = hibernateTemplate.findByCriteria(criteria);
        return events.size();
    }

    @Override
    public List<EventOfMatch> getEventPartList(long matchId, long teamId, int partNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EventOfMatch.class);

        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("teamId", teamId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        criteria.addOrder(Order.asc("timeSecond"));
        List<EventOfMatch> events = (List<EventOfMatch>) hibernateTemplate.findByCriteria(criteria);

        return events;
    }

    @Override
    public void deleteXiaoJieJieShuEvent(long matchId, long teamId, int partNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EventOfMatch.class);

        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("teamId", teamId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        criteria.addOrder(Order.desc("timeSecond"));
        List<EventOfMatch> events = (List<EventOfMatch>) hibernateTemplate.findByCriteria(criteria);

        for (EventOfMatch eventOfMatch : events) {
            hibernateTemplate.delete(eventOfMatch);
        }
    }

    @Override
    public EventOfMatch getEventBy(long eventId) {
        return hibernateTemplate.get(EventOfMatch.class, eventId);
    }

    @Override
    public void deleteEvent(EventOfMatch eventOfMatch) {
        hibernateTemplate.delete(eventOfMatch);
    }
}
