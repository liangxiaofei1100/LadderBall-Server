package com.zhaoyan.ladderball.service.team;

import com.zhaoyan.ladderball.dao.account.WeiXinUserDao;
import com.zhaoyan.ladderball.dao.team.FootballTeamDao;
import com.zhaoyan.ladderball.dao.wexinuserfootballteam.WeiXinUserFootballTeamDao;
import com.zhaoyan.ladderball.domain.account.db.WeiXinUser;
import com.zhaoyan.ladderball.domain.team.db.FootballTeam;
import com.zhaoyan.ladderball.domain.team.http.*;
import com.zhaoyan.ladderball.domain.wexinuserfootballteam.db.WeiXinUserFooballTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootballTeamService {

    @Autowired
    @Qualifier("hibernateFootballTeamDao")
    FootballTeamDao footballTeamDao;

    @Autowired
    @Qualifier("hibernateWeiXinUserDao")
    WeiXinUserDao weiXinUserDao;

    @Autowired
    @Qualifier("hibernateWeiXinUserFootballTeamDao")
    WeiXinUserFootballTeamDao weiXinUserFootballTeamDao;

    private static BeanCopier copierFootballTeamAddRequestToFootballTeam =
            BeanCopier.create(FootballTeamAddRequest.class, FootballTeam.class, false);

    /**
     * 创建一个球队
     */
    public FootballTeamAddResponse addFootballTeam(FootballTeamAddRequest request) {
        FootballTeamAddResponse response = new FootballTeamAddResponse();

        // 检查队名
        FootballTeam team = footballTeamDao.getFootballTeamByName(request.name);
        if (team != null) {
            response.buildFail("队名已存在");
            return response;
        }

        // 检查创建者
        WeiXinUser user = weiXinUserDao.getWeiXinUserByWeiXinId(request.weiXinId);
        if (user == null) {
            response.buildFail("无法找到此用户");
            return response;
        }

        // 添加球队
        team = new FootballTeam();
        copierFootballTeamAddRequestToFootballTeam.copy(request, team, null);
        footballTeamDao.addFootballTeam(team);
        response.teamId = team.id;

        // 添加创建者为队长
        WeiXinUserFooballTeam weiXinUserFooballTeam = new WeiXinUserFooballTeam();
        weiXinUserFooballTeam.footballTeam = team;
        weiXinUserFooballTeam.weiXinUser = user;
        weiXinUserFooballTeam.isCaptain = true;
        weiXinUserFootballTeamDao.addWeiXinUserFooballTeam(weiXinUserFooballTeam);

        response.buildOk();
        return response;
    }

    /**
     * 我的球队
     */
    public FootballTeamListMyTeamResponse getMyFootballTeams(FootballTeamAddRequest request) {
        FootballTeamListMyTeamResponse response = new FootballTeamListMyTeamResponse();
        response.teams = new ArrayList<>();

        List<WeiXinUserFooballTeam> weiXinUserFooballTeamList = weiXinUserFootballTeamDao.getWeiXinUserFooballTeamByWeiXinId(request.weiXinId);
        for (WeiXinUserFooballTeam weiXinUserFooballTeam : weiXinUserFooballTeamList) {
            FootballTeamListMyTeamResponse.FootballTeam team = new FootballTeamListMyTeamResponse.FootballTeam();
            FootballTeam footballTeam = weiXinUserFooballTeam.footballTeam;
            if (footballTeam != null) {
                team.id = footballTeam.id;
                team.name = footballTeam.name;
                team.address = footballTeam.address;

                WeiXinUserFooballTeam captionInfo = weiXinUserFootballTeamDao.getWeiXinUserFooballTeamCaptitainByTeamId(footballTeam.id);
                if (captionInfo != null && captionInfo.weiXinUser != null) {
                    team.captain = captionInfo.weiXinUser.nickName;
                }

                List<WeiXinUserFooballTeam> weiXinUserFooballTeams =
                        weiXinUserFootballTeamDao.getWeiXinUserFooballTeamByTeamId(footballTeam.id);
                team.playerNumber = weiXinUserFooballTeams.size();
            }

            response.teams.add(team);
        }

        response.buildOk();
        return response;
    }

    private static BeanCopier copierFootballTeamToFootTeamInfoResponse =
            BeanCopier.create(FootballTeam.class, FootTeamInfoResponse.TeamInfo.class, false);

    /**
     * 球队信息
     */
    public FootTeamInfoResponse getFootballTeamInfo(FootTeamInfoRequest request) {
        FootTeamInfoResponse response = new FootTeamInfoResponse();

        FootballTeam footballTeam = footballTeamDao.getFootballTeamById(request.teamId);
        if (footballTeam == null) {
            response.buildFail("没有找到球队");
            return response;
        }

        response.teamInfo = new FootTeamInfoResponse.TeamInfo();
        copierFootballTeamToFootTeamInfoResponse.copy(footballTeam, response.teamInfo, null);
        // 队长信息
        WeiXinUserFooballTeam captionInfo = weiXinUserFootballTeamDao.getWeiXinUserFooballTeamCaptitainByTeamId(footballTeam.id);
        if (captionInfo != null && captionInfo.weiXinUser != null) {
            response.teamInfo.captain = captionInfo.weiXinUser.nickName;

            if (captionInfo.weiXinUser.weiXinId.equals(request.weiXinId)) {
                response.teamInfo.isIAmCaptain = true;
            }
        }

        // 球员数
        List<WeiXinUserFooballTeam> weiXinUserFooballTeams =
                weiXinUserFootballTeamDao.getWeiXinUserFooballTeamByTeamId(footballTeam.id);
        response.teamInfo.playerNumber = weiXinUserFooballTeams.size();

        response.buildOk();
        return response;
    }
}
