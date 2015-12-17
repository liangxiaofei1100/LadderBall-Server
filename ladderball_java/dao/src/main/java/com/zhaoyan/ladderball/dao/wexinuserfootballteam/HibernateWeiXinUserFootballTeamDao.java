package com.zhaoyan.ladderball.dao.wexinuserfootballteam;

import com.zhaoyan.ladderball.domain.wexinuserfootballteam.db.WeiXinUserFooballTeam;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateWeiXinUserFootballTeamDao implements WeiXinUserFootballTeamDao{

    @Autowired
    HibernateTemplate hibernateTemplate;


    @Override
    public List<WeiXinUserFooballTeam> getWeiXinUserFooballTeamByWeiXinId(String weiXinId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WeiXinUserFooballTeam.class);
        // 多表查询时，必须建立引用，才能导航到属性
        criteria.createAlias("weiXinUser", "u");
        criteria.add(Restrictions.eq("u.weiXinId", weiXinId));

        List<WeiXinUserFooballTeam> weiXinUserFooballTeams = (List<WeiXinUserFooballTeam>) hibernateTemplate.findByCriteria(criteria);
        return weiXinUserFooballTeams;
    }

    @Override
    public WeiXinUserFooballTeam getWeiXinUserFooballTeamById(long id) {
        return hibernateTemplate.get(WeiXinUserFooballTeam.class, id);
    }

    @Override
    public WeiXinUserFooballTeam getWeiXinUserFooballTeamCaptitainByTeamId(long footballTeamId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WeiXinUserFooballTeam.class);
        criteria.add(Restrictions.eq("footballTeam.id", footballTeamId));
        criteria.add(Restrictions.eq("isCaptain", true));

        List<WeiXinUserFooballTeam> weiXinUserFooballTeams = (List<WeiXinUserFooballTeam>) hibernateTemplate.findByCriteria(criteria);
        if (!weiXinUserFooballTeams.isEmpty()) {
            return weiXinUserFooballTeams.get(0);
        }
        return null;
    }

    @Override
    public List<WeiXinUserFooballTeam> getWeiXinUserFooballTeamByTeamId(long footballTeamId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WeiXinUserFooballTeam.class);
        criteria.add(Restrictions.eq("footballTeam.id", footballTeamId));

        List<WeiXinUserFooballTeam> weiXinUserFooballTeams = (List<WeiXinUserFooballTeam>) hibernateTemplate.findByCriteria(criteria);
        return weiXinUserFooballTeams;
    }

    @Override
    public void addWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam) {
        hibernateTemplate.save(weiXinUserFooballTeam);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam) {
        hibernateTemplate.update(weiXinUserFooballTeam);
    }

    @Override
    public void deleteWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam) {
        hibernateTemplate.delete(weiXinUserFooballTeam);
    }
}
