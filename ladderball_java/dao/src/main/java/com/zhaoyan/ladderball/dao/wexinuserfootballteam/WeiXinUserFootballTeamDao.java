package com.zhaoyan.ladderball.dao.wexinuserfootballteam;

import com.zhaoyan.ladderball.domain.wexinuserfootballteam.db.WeiXinUserFooballTeam;

import java.util.List;

public interface WeiXinUserFootballTeamDao {

    List<WeiXinUserFooballTeam> getWeiXinUserFooballTeamByWeiXinId(String weiXinId);

    WeiXinUserFooballTeam getWeiXinUserFooballTeamById(long id);

    WeiXinUserFooballTeam getWeiXinUserFooballTeamCaptitainByTeamId(long footballTeamId);

    List<WeiXinUserFooballTeam> getWeiXinUserFooballTeamByTeamId(long footballTeamId);

    void addWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam);

    void modifyWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam);

    void deleteWeiXinUserFooballTeam(WeiXinUserFooballTeam weiXinUserFooballTeam);
}
