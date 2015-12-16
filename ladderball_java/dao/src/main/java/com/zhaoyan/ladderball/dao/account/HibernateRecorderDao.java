package com.zhaoyan.ladderball.dao.account;


import com.zhaoyan.ladderball.domain.account.db.Recorder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HibernateRecorderDao implements RecorderDao {
    Logger logger = LoggerFactory.getLogger(HibernateRecorderDao.class);

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public Recorder getRecorderByPhone(String phone, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Recorder.class);
        criteria.add(Restrictions.eq("phone",phone))
                .add(Restrictions.eq("password", password));

        List<Recorder> recorders = (List<Recorder>) hibernateTemplate.findByCriteria(criteria, 0, 1);
        if (!recorders.isEmpty()) {
            return recorders.get(0);
        }
        return null;
    }

    @Override
    public Recorder getRecorderByPhone(String phone) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Recorder.class);
        criteria.add(Restrictions.eq("phone",phone));

        List<Recorder> recorders = (List<Recorder>) hibernateTemplate.findByCriteria(criteria, 0, 1);
        if (!recorders.isEmpty()) {
            return recorders.get(0);
        }
        return null;
    }

    @Override
    public boolean setPassword(long recorderId, String oldPassword, String newPassword) {
        Recorder recorder = hibernateTemplate.get(Recorder.class, recorderId);
        if (recorder == null) {
            logger.warn("Set password fail. No recorder with id: " + recorderId);
            return false;
        }

        logger.debug("setPassword(), recorder: " + recorder);
        if (recorder.password != null && recorder.password.equals(oldPassword)) {
            recorder.password = newPassword;
            hibernateTemplate.update(recorder);
            return true;
        } else {
            // 密码错误
            return false;
        }
    }

    @Override
    public Recorder getRecorderById(long id) {
        return hibernateTemplate.get(Recorder.class, id);
    }

    @Override
    public void modifyRecorder(Recorder recorder) {
        hibernateTemplate.update(recorder);
    }

    @Override
    public void deleteRecorder(Recorder recorder) {
        hibernateTemplate.delete(recorder);
    }

    @Override
    public void addRecorder(Recorder recorder) {
        hibernateTemplate.save(recorder);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }
}
