package com.zhaoyan.ladderball.service.event.handler;

import com.zhaoyan.ladderball.dao.eventofmatch.EventOfMatchDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.domain.eventofmatch.EventCode;
import com.zhaoyan.ladderball.domain.eventofmatch.db.EventOfMatch;
import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;

public class UpdateCountEventHandler extends EventHandler {

    public static int[] EVENTS = {
            // | 助攻		| 10002		|
            EventCode.EVENT_ZHU_GONG,
            // | 角球		| 10003		|
            EventCode.EVENT_JIAO_QIU,
            //  | 任意球		| 10004		|
            EventCode.EVENT_REN_YI_QIU,
            //  | 边界球		| 10005		|
            EventCode.EVENT_BIAN_JIE_QIU,
            // | 越位		| 10006		|
            EventCode.EVENT_YUE_WEI,
            // | 失误		| 10007		|
            EventCode.EVENT_SHI_WU,
            // | 过人成功	| 10008		|
            EventCode.EVENT_GUO_REN_CHENG_GONG,
            // | 过人失败	| 10009		|
            EventCode.EVENT_GUO_REN_SHI_BAI,
            // | 射正		| 10010		|
            EventCode.EVENT_SHE_ZHENG,
            // | 射偏		| 10011		|
            EventCode.EVENT_SHE_PIAN,
            // | 射门被堵	| 10012		|
            EventCode.EVENT_SHE_MEN_BEI_DU,
            // | 传球成功	| 10013		|
            EventCode.EVENT_CHUAN_QIU_CHENG_GONG,
            // | 威胁球		| 10014		|
            EventCode.EVENT_WEI_XIE_QIU,
            // | 传球失败	| 10015		|
            EventCode.EVENT_CHUAN_QIU_SHI_BAI,
            // | 封堵射门	| 10016		|
            EventCode.EVENT_FENG_DU_SHE_MEN,
            // | 拦截		| 10017		|
            EventCode.EVENT_LAN_JIE,
            // | 抢断		| 10018		|
            EventCode.EVENT_QIANG_DUAN,
            // | 解围		| 10019		|
            EventCode.EVENT_JIE_WEI,
            // | 补救射门	| 10020		|
            EventCode.EVENT_BU_JIU_SHE_MEN,
            // | 补救单刀	| 10021		|
            EventCode.EVENT_BU_JIU_DAN_DAO,
            // | 手抛球		| 10022		|
            EventCode.EVENT_SHOU_PAO_QIU,
            // | 球门球		| 10023		|
            EventCode.EVENT_QIU_MEN_QIU,
            // | 黄牌		| 10024		|
            EventCode.EVENT_HUANG_PAI,
            // | 红牌		| 10025		|
            EventCode.EVENT_HONG_PAI,
            // | 犯规		| 10026		|
            EventCode.EVENT_FAN_GUI,
            // | 换人		| 10028		|
            EventCode.EVENT_HUAN_REN,
    };

    @Override
    public boolean handleAddEvent(EventOfMatch event) {
        // 更新球员数据
        return updatePlayerOfMatch(event.eventCode, event.playerOfMatch.id);
    }

    @Override
    public boolean handleDeleteEvent(EventOfMatch event) {
        if (event.playerOfMatch != null) {
            // 更新球员数据
            return updatePlayerOfMatch(event.eventCode, event.playerOfMatch.id);
        } else {
            // 事件没有关联到球员，不需要更新球员数据
            return true;
        }
    }

    private boolean updatePlayerOfMatch(int eventCode, long playerId) {
        // 获取事件总个数
        EventOfMatchDao eventOfMatchDao = getEventOfMatchDao();
        int eventCount = eventOfMatchDao.getEventCountByPlayer(eventCode, playerId);
        // 更新个人事件个数
        PlayerOfMatchDao playerOfMatchDao = getPlayerOfMatchDao();
        PlayerOfMatch playerOfMatch = playerOfMatchDao.getPlayerById(playerId);

        // 更新事件个数
        updatePlayerOfMatchEvent(playerOfMatch, eventCount, eventCode);

        playerOfMatchDao.modifyPlayer(playerOfMatch);
        return true;
    }

    /**
     *
     */
    private void updatePlayerOfMatchEvent(PlayerOfMatch playerOfMatch, int eventCount, int eventCode) {
        switch (eventCode) {
            case EventCode.EVENT_ZHU_GONG:
                // | 助攻		| 10002		|
                playerOfMatch.event10002 = eventCount;
                break;
            case EventCode.EVENT_JIAO_QIU:
                // | 角球		| 10003		|
                playerOfMatch.event10003 = eventCount;
                break;
            case EventCode.EVENT_REN_YI_QIU:
                //  | 任意球		| 10004		|
                playerOfMatch.event10004 = eventCount;
                break;
            case EventCode.EVENT_BIAN_JIE_QIU:
                //  | 边界球		| 10005		|
                playerOfMatch.event10005 = eventCount;
                break;
            case EventCode.EVENT_YUE_WEI:
                // | 越位		| 10006		|
                playerOfMatch.event10006 = eventCount;
                break;
            case EventCode.EVENT_SHI_WU:
                // | 失误		| 10007		|
                playerOfMatch.event10007 = eventCount;
                break;
            case EventCode.EVENT_GUO_REN_CHENG_GONG:
                // | 过人成功	| 10008		|
                playerOfMatch.event10008 = eventCount;
                break;
            case EventCode.EVENT_GUO_REN_SHI_BAI:
                // | 过人失败	| 10009		|
                playerOfMatch.event10009 = eventCount;
                break;
            case EventCode.EVENT_SHE_ZHENG:
                // | 射正		| 10010		|
                playerOfMatch.event10010 = eventCount;
                break;
            case EventCode.EVENT_SHE_PIAN:
                // | 射偏		| 10011		|
                playerOfMatch.event10011 = eventCount;
                break;
            case EventCode.EVENT_SHE_MEN_BEI_DU:
                // | 射门被堵	| 10012		|
                playerOfMatch.event10012 = eventCount;
                break;
            case EventCode.EVENT_CHUAN_QIU_CHENG_GONG:
                // | 传球成功	| 10013		|
                playerOfMatch.event10013 = eventCount;
                break;
            case EventCode.EVENT_WEI_XIE_QIU:
                // | 威胁球		| 10014		|
                playerOfMatch.event10014 = eventCount;
                break;
            case EventCode.EVENT_CHUAN_QIU_SHI_BAI:
                // | 传球失败	| 10015		|
                playerOfMatch.event10015 = eventCount;
                break;
            case EventCode.EVENT_FENG_DU_SHE_MEN:
                // | 封堵射门	| 10016		|
                playerOfMatch.event10016 = eventCount;
                break;
            case EventCode.EVENT_LAN_JIE:
                // | 拦截		| 10017		|
                playerOfMatch.event10017 = eventCount;
                break;
            case EventCode.EVENT_QIANG_DUAN:
                // | 抢断		| 10018		|
                playerOfMatch.event10018 = eventCount;
                break;
            case EventCode.EVENT_JIE_WEI:
                // | 解围		| 10019		|
                playerOfMatch.event10019 = eventCount;
                break;
            case EventCode.EVENT_BU_JIU_SHE_MEN:
                // | 补救射门	| 10020		|
                playerOfMatch.event10020 = eventCount;
                break;
            case EventCode.EVENT_BU_JIU_DAN_DAO:
                // | 补救单刀	| 10021		|
                playerOfMatch.event10021 = eventCount;
                break;
            case EventCode.EVENT_SHOU_PAO_QIU:
                // | 手抛球		| 10022		|
                playerOfMatch.event10022 = eventCount;
                break;
            case EventCode.EVENT_QIU_MEN_QIU:
                // | 球门球		| 10023		|
                playerOfMatch.event10023 = eventCount;
                break;
            case EventCode.EVENT_HUANG_PAI:
                // | 黄牌		| 10024		|
                playerOfMatch.event10024 = eventCount;
                break;
            case EventCode.EVENT_HONG_PAI:
                // | 红牌		| 10025		|
                playerOfMatch.event10025 = eventCount;
                break;
            case EventCode.EVENT_FAN_GUI:
                // | 犯规		| 10026		|
                playerOfMatch.event10026 = eventCount;
                break;
            case EventCode.EVENT_HUAN_REN:
                // | 换人		| 10028		|
                playerOfMatch.event10028 = eventCount;
                break;
        }
    }
}
