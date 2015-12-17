package com.zhaoyan.ladderball.weixin.team;

import com.zhaoyan.ladderball.domain.team.http.FootballTeamAddRequest;
import com.zhaoyan.ladderball.domain.team.http.FootballTeamAddResponse;
import com.zhaoyan.ladderball.domain.team.http.FootballTeamListMyTeamResponse;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    FootballTeamAddResponse addFootballTeam(@RequestBody FootballTeamAddRequest request) {
        logger.debug("addFootballTeam() FootballTeamAddRequest: " + request);
        FootballTeamAddResponse response = footballTeamService.addFootballTeam(request);
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


}
