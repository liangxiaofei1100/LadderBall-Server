package com.zhaoyan.ladderball.app.account;

import com.zhaoyan.ladderball.app.hello.HelloController;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginResponse;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordResponse;
import com.zhaoyan.ladderball.service.account.RecorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account/recorder")
public class RecorderController {
    Logger logger = LoggerFactory.getLogger(RecorderController.class);

    @Autowired
    RecorderService recorderService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    RecorderLoginResponse login(@RequestBody RecorderLoginRequest request) {
        logger.debug("login() RecorderLoginRequest: " + request);
        RecorderLoginResponse response = recorderService.login(request);
        return response;
    }

    @RequestMapping(value = "/password/set", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    RecorderSetPasswordResponse login(@RequestBody RecorderSetPasswordRequest request) {
        logger.debug("login() RecorderSetPasswordRequest: " + request);
        RecorderSetPasswordResponse response = recorderService.setPassword(request);
        return response;
    }
}
