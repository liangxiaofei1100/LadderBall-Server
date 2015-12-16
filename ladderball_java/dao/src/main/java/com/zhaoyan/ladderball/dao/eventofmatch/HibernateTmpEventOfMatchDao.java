package com.zhaoyan.ladderball.dao.eventofmatch;

import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateTmpEventOfMatchDao implements TmpEventOfMatchDao {

    @Autowired
    HibernateTemplate hibernateTemplate;


    @Override
    public TmpEventOfMatch getEventBy(long eventId) {
        return hibernateTemplate.get(TmpEventOfMatch.class, eventId);
    }

    @Override
    public void addEvent(TmpEventOfMatch eventOfMatch) {
        hibernateTemplate.save(eventOfMatch);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void updateEvent(TmpEventOfMatch eventOfMatch) {
        hibernateTemplate.update(eventOfMatch);
    }

    @Override
    public void deleteEvent(TmpEventOfMatch eventOfMatch) {
        hibernateTemplate.delete(eventOfMatch);
    }

    @Override
    public int getEventCountByPlayer(int eventCode, long playerId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpEventOfMatch.class);
        criteria.add(Restrictions.eq("eventCode", eventCode));
        criteria.add(Restrictions.eq("playerOfMatch.id", playerId));
        List<?> events = hibernateTemplate.findByCriteria(criteria);
        return events.size();
    }

    @Override
    public List<TmpEventOfMatch> getEventPartList(long matchId, long teamId, int partNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpEventOfMatch.class);

        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("teamId", teamId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        criteria.addOrder(Order.asc("timeSecond"));
        List<TmpEventOfMatch> events = (List<TmpEventOfMatch>) hibernateTemplate.findByCriteria(criteria);

        return events;
    }

    @Override
    public void deleteXiaoJieJieShuEvent(long matchId, long teamId, int partNumber, int eventCode) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpEventOfMatch.class);

        criteria.add(Restrictions.eq("matchId", matchId));
        criteria.add(Restrictions.eq("teamId", teamId));
        criteria.add(Restrictions.eq("partNumber", partNumber));
        criteria.add(Restrictions.eq("eventCode", eventCode));
        criteria.addOrder(Order.desc("timeSecond"));
        List<TmpEventOfMatch> events = (List<TmpEventOfMatch>) hibernateTemplate.findByCriteria(criteria);

        for (TmpEventOfMatch eventOfMatch : events) {
            hibernateTemplate.delete(eventOfMatch);
        }
    }

    @Override
    public boolean isEventUUIDRepeated(String uuid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TmpEventOfMatch.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        List<?> events = hibernateTemplate.findByCriteria(criteria);
        return !events.isEmpty();
    }
}
