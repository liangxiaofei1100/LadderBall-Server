package com.zhaoyan.ladderball.dao.teamofmatch;


import com.zhaoyan.ladderball.domain.teamofmatch.db.TmpTeamOfMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTmpTeamOfMatchDao implements TmpTeamOfMatchDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public TmpTeamOfMatch getTeamOfMatch(long tmpTeamOfMatchId) {
        return hibernateTemplate.get(TmpTeamOfMatch.class, tmpTeamOfMatchId);
    }

    @Override
    public TmpTeamOfMatch addTeamOfMatch(TmpTeamOfMatch team) {
        hibernateTemplate.save(team);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return team;
    }

    @Override
    public void modifyTeamOfMatch(TmpTeamOfMatch team) {
        hibernateTemplate.update(team);
    }

    @Override
    public void deleteTeamOfMatch(TmpTeamOfMatch team) {
        hibernateTemplate.delete(team);
    }
}
