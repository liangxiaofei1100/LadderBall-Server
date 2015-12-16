package com.zhaoyan.ladderball.app.match;

import com.zhaoyan.ladderball.domain.match.http.*;
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
        logger.debug("getRecorderMatchList() MatchListRequest: " + request);
        MatchListResponse response = matchService.getRecorderMatchList(request);
        return response;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchDetailResponse getMatchDetail(@RequestBody MatchDetailRequest request) {
        logger.debug("getMatchDetail() MatchDetailRequest: " + request);
        MatchDetailResponse response = matchService.getMatchDetail(request);
        return response;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchModifyResponse modifyMatch(@RequestBody MatchModifyRequest request) {
        logger.debug("getMatchDetail() MatchModifyRequest: " + request);
        MatchModifyResponse response = matchService.modifyMatch(request);
        return response;
    }

    /**
     * 添加球员
     */
    @RequestMapping(value = "/player/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAddPlayerResponse addPlayer(@RequestBody MatchAddPlayerRequest request) {
        logger.debug("addPlayer() MatchAddPlayerRequest: " + request);
        MatchAddPlayerResponse response = matchService.addPlayer(request);
        return response;
    }

    /**
     * 提交任务
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchSubmitResponse submitMatch(@RequestBody MatchSubmitRequest request) {
        logger.debug("submitMatch() MatchSubmitRequest: " + request);
        MatchSubmitResponse response = matchService.submitMatch(request);
        return response;
    }
}
