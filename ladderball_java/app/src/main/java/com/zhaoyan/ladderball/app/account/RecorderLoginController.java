package com.zhaoyan.ladderball.app.account;

import com.zhaoyan.ladderball.app.hello.HelloController;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginResponse;
import com.zhaoyan.ladderball.service.account.RecorderLoginService;
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
public class RecorderLoginController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RecorderLoginService recorderLoginService;

    @RequestMapping(value = "/recorderlogin", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    RecorderLoginResponse login(@RequestBody RecorderLoginRequest request) {
        logger.debug("login() RecorderLoginRequest: " + request);
        RecorderLoginResponse response = recorderLoginService.login(request);
        return response;
    }
}
