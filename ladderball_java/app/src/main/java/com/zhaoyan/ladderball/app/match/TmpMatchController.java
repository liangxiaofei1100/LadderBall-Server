package com.zhaoyan.ladderball.app.match;

import com.zhaoyan.ladderball.domain.match.http.*;
import com.zhaoyan.ladderball.service.match.MatchService;
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
    TmpMatchListResponse getTmpMatchList(@RequestBody TmpMatchListRequest request) {
        logger.debug("getTmpMatchList() TmpMatchListRequest: " + request);
        TmpMatchListResponse response = tmpMatchService.getTmpMatchList(request);
        return response;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    TmpMatchDetailResponse getMatchDetail(@RequestBody TmpMatchDetailRequest request) {
        logger.debug("getMatchDetail() TmpMatchDetailRequest: " + request);
        TmpMatchDetailResponse response = tmpMatchService.getTmpMatchDetail(request);
        return response;
    }
}
