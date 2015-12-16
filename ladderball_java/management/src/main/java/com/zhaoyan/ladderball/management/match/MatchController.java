package com.zhaoyan.ladderball.management.match;

import com.zhaoyan.ladderball.domain.match.http.MatchAddRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchAddResponse;
import com.zhaoyan.ladderball.domain.match.http.MatchAllListRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchAllListResponse;
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
}
