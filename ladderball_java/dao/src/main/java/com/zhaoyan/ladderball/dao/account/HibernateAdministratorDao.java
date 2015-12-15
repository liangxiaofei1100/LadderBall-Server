package com.zhaoyan.ladderball.dao.account;


import com.zhaoyan.ladderball.domain.account.db.Administrator;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateAdministratorDao implements AdministratorDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public Administrator getAdministrator(String userName, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Administrator.class);
        criteria.add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password));

        List<Administrator> administrators = (List<Administrator>) hibernateTemplate.findByCriteria(criteria, 0, 1);
        if (!administrators.isEmpty()) {
            return administrators.get(0);
        }
        return null;
    }

    @Override
    public Administrator getAdministrator(String userName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Administrator.class);
        criteria.add(Restrictions.eq("userName", userName));

        List<Administrator> administrators = (List<Administrator>) hibernateTemplate.findByCriteria(criteria, 0, 1);
        if (!administrators.isEmpty()) {
            return administrators.get(0);
        }
        return null;
    }
}
