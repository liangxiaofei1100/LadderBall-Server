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

    /**
     * 获取已领取的练习赛
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchListResponse getTmpMatchList(@RequestBody MatchListRequest request) {
        logger.debug("getTmpMatchList() MatchListRequest: " + request);
        MatchListResponse response = tmpMatchService.getMatchList(request);
        return response;
    }

    /**
     * 获取未领取的练习赛
     */
    @RequestMapping(value = "/toasignlist", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchListResponse getTmpMatchToAsignList(@RequestBody MatchListRequest request) {
        logger.debug("getTmpMatchToAsignList() MatchListRequest: " + request);
        MatchListResponse response = tmpMatchService.getMatchToAsignList(request);
        return response;
    }

    /**
     * 记录者领取练习赛，只能领取客场队伍
     */
    @RequestMapping(value = "/asignvisitor", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    TmpMatchAsignVisitorResponse asignTmpMatchVisitor(@RequestBody TmpMatchAsignVisitorRequest request) {
        logger.debug("asignTmpMatchVisitor() TmpMatchAsignVisitorRequest: " + request);
        TmpMatchAsignVisitorResponse response = tmpMatchService.asignTmpMatchVisitor(request);
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

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    MatchAddResponse addMatch(@RequestBody MatchAddRequest request) {
        logger.debug("addMatch() MatchAddRequest: " + request);
        MatchAddResponse response = tmpMatchService.addMatch(request);
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
        MatchAddPlayerResponse response = tmpMatchService.addPlayer(request);
        return response;
    }
}
