package com.zhaoyan.ladderball.management.match;

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

    @RequestMapping(value = "/list/all", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAllListResponse getAllMatch(@RequestBody MatchAllListRequest request) {
        logger.debug("getAllMatch() MatchAllListRequest: " + request);
        MatchAllListResponse response = matchService.getAllMatch(request);
        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAddResponse addMatch(@RequestBody MatchAddRequest request) {
        logger.debug("addMatch() MatchAddRequest: " + request);
        MatchAddResponse response = matchService.addMatch(request);
        return response;
    }

    @RequestMapping(value = "/asign", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAsignResponse asignMatch(@RequestBody MatchAsignRequest request) {
        logger.debug("asignMatch() MatchAsignRequest: " + request);
        MatchAsignResponse response = matchService.asignMatch(request);
        return response;
    }

    /**
     * 取消比赛分配给记录员
     */
    @RequestMapping(value = "/asign/delete", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAsignDeleteResponse asignMatchDelete(@RequestBody MatchAsignDeleteRequest request) {
        logger.debug("asignMatchDelete() MatchAsignDeleteRequest: " + request);
        MatchAsignDeleteResponse response = matchService.asignMatchDelete(request);
        return response;
    }
}
