package com.zhaoyan.ladderball.dao.recordermatch;

import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import org.hibernate.criterion.DetachedCriteria;
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
    public List<RecorderMatch> getRecorderMatch(long recorderId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        List<RecorderMatch> recorderMatches = (List<RecorderMatch>) hibernateTemplate.findByCriteria(criteria);
        return recorderMatches;
    }
}
