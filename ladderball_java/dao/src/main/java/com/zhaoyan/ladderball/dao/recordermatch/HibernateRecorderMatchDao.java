package com.zhaoyan.ladderball.dao.recordermatch;

import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRecorderMatchDao implements RecorderMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<RecorderMatch> getRecorderMatchByRecorder(long recorderId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        List<RecorderMatch> recorderMatches = (List<RecorderMatch>) hibernateTemplate.findByCriteria(criteria);
        return recorderMatches;
    }

    @Override
    public List<RecorderMatch> getRecorderMatchByRecorder(long recorderId, boolean isComplete) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        // 多表查询时，必须建立引用，才能导航到属性
        criteria.createAlias("match", "m");
        criteria.add(Restrictions.eq("m.complete", isComplete));
        if (isComplete) {
            criteria.addOrder(Order.asc("m.startTime"));
        } else {
            criteria.addOrder(Order.desc("m.startTime"));
        }

        List<RecorderMatch> recorderMatches = (List<RecorderMatch>) hibernateTemplate.findByCriteria(criteria);
        return recorderMatches;
    }

    @Override
    public RecorderMatch getRecorderMatchByRecorderMatch(long recorderId, long matchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        criteria.add(Restrictions.eq("match.id", matchId));
        List<RecorderMatch> recorderMatches = (List<RecorderMatch>) hibernateTemplate.findByCriteria(criteria);
        if (!recorderMatches.isEmpty()) {
            return recorderMatches.get(0);
        }
        return null;
    }
}
