package com.zhaoyan.ladderball.app.account;

import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordResponse;
import com.zhaoyan.ladderball.service.account.RecorderModifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class RecorderModifyController {
    Logger logger = LoggerFactory.getLogger(RecorderModifyController.class);

    @Autowired
    RecorderModifyService recorderModifyService;

    @RequestMapping(value = "/recorderSetPassword", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    RecorderSetPasswordResponse login(@RequestBody RecorderSetPasswordRequest request) {
        logger.debug("login() RecorderSetPasswordRequest: " + request);
        RecorderSetPasswordResponse response = recorderModifyService.setPassword(request);
        return response;
    }

}
