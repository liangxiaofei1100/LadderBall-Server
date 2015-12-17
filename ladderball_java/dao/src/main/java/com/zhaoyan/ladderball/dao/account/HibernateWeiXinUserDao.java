package com.zhaoyan.ladderball.dao.account;

import com.zhaoyan.ladderball.domain.account.db.WeiXinUser;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateWeiXinUserDao implements WeiXinUserDao{

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public WeiXinUser getWeiXinUserById(long id) {
        return hibernateTemplate.get(WeiXinUser.class, id);
    }

    @Override
    public WeiXinUser getWeiXinUserByWeiXinId(String weiXinId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WeiXinUser.class);
        criteria.add(Restrictions.eq("weiXinId", weiXinId));

        List<WeiXinUser> users = (List<WeiXinUser>) hibernateTemplate.findByCriteria(criteria, 0, 1);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void addWeiXinUser(WeiXinUser user) {
        hibernateTemplate.save(user);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    @Override
    public void modifyWeiXinUser(WeiXinUser user) {
        hibernateTemplate.update(user);
    }

    @Override
    public void deleteWeiXinUser(WeiXinUser user) {
        hibernateTemplate.delete(user);
    }
}
