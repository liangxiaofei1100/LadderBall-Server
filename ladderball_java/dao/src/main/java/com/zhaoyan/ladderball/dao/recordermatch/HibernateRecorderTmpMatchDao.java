package com.zhaoyan.ladderball.dao.recordermatch;


import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRecorderTmpMatchDao implements RecorderTmpMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public List<RecorderTmpMatch> getRecorderTmpMatchByRecorder(long recorderId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderTmpMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        List<RecorderTmpMatch> recorderTmpMatches = (List<RecorderTmpMatch>) hibernateTemplate.findByCriteria(criteria);
        return recorderTmpMatches;
    }

    @Override
    public RecorderTmpMatch getRecorderTmpMatchByRecorderMatch(long recorderId, long tmpMatchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderTmpMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        criteria.add(Restrictions.eq("matchId", tmpMatchId));
        List<RecorderTmpMatch> recorderMatches = (List<RecorderTmpMatch>) hibernateTemplate.findByCriteria(criteria);
        if (!recorderMatches.isEmpty()) {
            return recorderMatches.get(0);
        }

        return null;
    }
}
