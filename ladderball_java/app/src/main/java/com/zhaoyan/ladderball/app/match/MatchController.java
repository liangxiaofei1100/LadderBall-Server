package com.zhaoyan.ladderball.app.match;

import com.zhaoyan.ladderball.domain.match.http.MatchListRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchListResponse;
import com.zhaoyan.ladderball.service.match.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/match")
public class MatchController {

    Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    MatchService matchService;


    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchListResponse getMatchList(@RequestBody MatchListRequest request) {
        logger.debug("getMatchList() MatchListRequest: " + request);
        MatchListResponse response = matchService.getMatchList(request);
        return response;
    }
}
