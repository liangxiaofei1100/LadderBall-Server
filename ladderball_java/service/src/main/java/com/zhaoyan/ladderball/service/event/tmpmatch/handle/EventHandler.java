package com.zhaoyan.ladderball.service.event.tmpmatch.handle;


import com.zhaoyan.ladderball.dao.eventofmatch.TmpEventOfMatchDao;
import com.zhaoyan.ladderball.dao.match.TmpMatchDao;
import com.zhaoyan.ladderball.dao.match.TmpMatchPartDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TmpTeamOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.db.TmpEventOfMatch;
import com.zhaoyan.ladderball.service.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 练习赛事件处理
 */
public abstract class EventHandler {
    Logger logger = LoggerFactory.getLogger(EventHandler.class);

    public TmpMatchDao getMatchDao() {
        return SpringContextUtil.getBean("hibernateTmpMatchDao");
    }

    public TmpMatchPartDao getMatchPartDao() {
        return SpringContextUtil.getBean("hibernateTmpMatchPartDao");
    }

    public TmpTeamOfMatchDao getTeamOfMatchDao() {
        return SpringContextUtil.getBean("hibernateTmpTeamOfMatchDao");
    }

    public TmpPlayerOfMatchDao getPlayerOfMatchDao() {
        return SpringContextUtil.getBean("hibernateTmpPlayerOfMatchDao");
    }

    public TmpEventOfMatchDao getEventOfMatchDao() {
        return SpringContextUtil.getBean("hibernateTmpEventOfMatchDao");
    }

    /**
     * 处理添加事件
     */
    public abstract boolean handleAddEvent(TmpEventOfMatch event);

    /**
     * 处理删除事件
     */
    public abstract boolean handleDeleteEvent(TmpEventOfMatch event);
}
