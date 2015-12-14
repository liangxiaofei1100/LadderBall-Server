package com.zhaoyan.ladderball.service.event.handler;


import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.match.MatchDao;
import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TeamOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest.Event;
import com.zhaoyan.ladderball.service.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EventHandler {
    Logger logger = LoggerFactory.getLogger(EventHandler.class);

    /**
     * 处理事件
     */
    abstract public boolean handleEvent(Event event);

    public MatchDao getMatchDao() {
        return SpringContextUtil.getBean("hibernateMatchDao");
    }

    public MatchPartDao getMatchPartDao() {
        return SpringContextUtil.getBean("hibernateMatchPartDao");
    }

    public TeamOfMatchDao getTeamOfMatchDao() {
        return SpringContextUtil.getBean("hibernateTeamOfMatchDao");
    }

    public PlayerOfMatchDao getPlayerOfMatchDao() {
        return SpringContextUtil.getBean("hibernatePlayerOfMatchDao");
    }

    public EventOfMatchDao getEventOfMatchDao() {
        return SpringContextUtil.getBean("hibernateEventOfMatchDao");
    }
}
