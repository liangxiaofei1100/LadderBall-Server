package com.zhaoyan.ladderball.weixin.team;

import com.zhaoyan.ladderball.domain.team.http.*;
import com.zhaoyan.ladderball.service.team.FootballTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/footballteam")
public class FootballTeamController {
    Logger logger = LoggerFactory.getLogger(FootballTeamController.class);

    @Autowired
    FootballTeamService footballTeamService;

    /**
     * 创建球队
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    FootballTeamAddResponse addFootballTeam(@RequestBody FootballTeamAddRequest request) {
        logger.debug("addFootballTeam() FootballTeamAddRequest: " + request);
        FootballTeamAddResponse response = footballTeamService.addFootballTeam(request);
        return response;
    }

    /**
     * 球队信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    FootTeamInfoResponse getFootballTeamInfo(@RequestBody FootTeamInfoRequest request) {
        logger.debug("getFootballTeamInfo() FootTeamInfoRequest: " + request);
        FootTeamInfoResponse response = footballTeamService.getFootballTeamInfo(request);
        return response;
    }


    /**
     * 我的球队
     */
    @RequestMapping(value = "/list/myteam", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    FootballTeamListMyTeamResponse getMyFootballTeams(@RequestBody FootballTeamAddRequest request) {
        logger.debug("getMyFootballTeams() FootballTeamAddRequest: " + request);
        FootballTeamListMyTeamResponse response = footballTeamService.getMyFootballTeams(request);
        return response;
    }

    /**
     * 球队球员
     */
    @RequestMapping(value = "/player/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    FootBallTeamPlayerListResponse getFootballTeamPlayers(@RequestBody FootBallTeamPlayerListRequest request) {
        logger.debug("getFootballTeamPlayers() FootBallTeamPlayerListRequest: " + request);
        FootBallTeamPlayerListResponse response = footballTeamService.getFootballTeamPlayers(request);
        return response;
    }

}
