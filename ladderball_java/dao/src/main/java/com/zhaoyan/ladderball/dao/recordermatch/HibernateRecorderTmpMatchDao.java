package com.zhaoyan.ladderball.dao.recordermatch;


import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRecorderTmpMatchDao implements RecorderTmpMatchDao{
    Logger logger = LoggerFactory.getLogger(HibernateRecorderTmpMatchDao.class);

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

    @Override
    public RecorderTmpMatch addRecorderTmpMatch(RecorderTmpMatch recorderTmpMatch) {
        hibernateTemplate.save(recorderTmpMatch);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return recorderTmpMatch;
    }

    @Override
    public List<RecorderTmpMatch> getToAsignTmpMatchByRecorder(long recorderId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderTmpMatch.class);
        // 认领的是其他记录者
        criteria.add(Restrictions.ne("recorderId", recorderId));
        // 认领的是主队
        criteria.add(Restrictions.eq("asignedTeam", RecorderTmpMatch.ASIGNED_TEAM_HOME));
        // 比赛的还有队伍没有被认领
        criteria.add(Restrictions.eq("isMatchAsigned", false));

        List<RecorderTmpMatch> recorderTmpMatches = (List<RecorderTmpMatch>) hibernateTemplate.findByCriteria(criteria);
        return recorderTmpMatches;
    }

    @Override
    public boolean isTmpMatchAsignedToRecorder(long recorderId, long matchId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderTmpMatch.class);
        criteria.add(Restrictions.eq("recorderId", recorderId));
        criteria.add(Restrictions.eq("matchId", matchId));
        List<RecorderTmpMatch> recorderTmpMatches = (List<RecorderTmpMatch>) hibernateTemplate.findByCriteria(criteria);

        return !recorderTmpMatches.isEmpty();
    }

    @Override
    public boolean asignTmpMatchVisitor(long recorderId, long matchId) {
        RecorderTmpMatch recorderTmpMatch = new RecorderTmpMatch();
        recorderTmpMatch.recorderId = recorderId;
        recorderTmpMatch.matchId = matchId;
        recorderTmpMatch.asignedTeam = RecorderTmpMatch.ASIGNED_TEAM_VISITOR;
        // 这场比赛的两个队伍都被认领
        recorderTmpMatch.isMatchAsigned = true;
        hibernateTemplate.save(recorderTmpMatch);

        // 将这场比赛的另一个认领者的比赛也修改为比赛已被认领完。
        DetachedCriteria criteria = DetachedCriteria.forClass(RecorderTmpMatch.class);
        // 认领的是其他记录者
        criteria.add(Restrictions.ne("recorderId", recorderId));
        criteria.add(Restrictions.eq("matchId", matchId));
        List<RecorderTmpMatch> recorderTmpMatches = (List<RecorderTmpMatch>) hibernateTemplate.findByCriteria(criteria);
        if (recorderTmpMatches != null) {
            for(RecorderTmpMatch r : recorderTmpMatches) {
                r.isMatchAsigned = true;
                hibernateTemplate.update(r);
            }
        }

        return true;
    }
}
