package com.zhaoyan.ladderball.app.match;

import com.zhaoyan.ladderball.domain.match.http.*;
import com.zhaoyan.ladderball.service.match.TmpMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tmpmatch")
public class TmpMatchController {
    Logger logger = LoggerFactory.getLogger(TmpMatchController.class);

    @Autowired
    TmpMatchService tmpMatchService;


    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchListResponse getTmpMatchList(@RequestBody MatchListRequest request) {
        logger.debug("getTmpMatchList() MatchListRequest: " + request);
        MatchListResponse response = tmpMatchService.getMatchList(request);
        return response;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchDetailResponse getMatchDetail(@RequestBody MatchDetailRequest request) {
        logger.debug("getMatchDetail() MatchDetailRequest: " + request);
        MatchDetailResponse response = tmpMatchService.getMatchDetail(request);
        return response;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    TmpMatchModifyResponse modifyMatch(@RequestBody TmpMatchModifyRequest request) {
        logger.debug("getMatchDetail() TmpMatchModifyRequest: " + request);
        TmpMatchModifyResponse response = tmpMatchService.modifyTmpMatch(request);
        return response;
    }
}
