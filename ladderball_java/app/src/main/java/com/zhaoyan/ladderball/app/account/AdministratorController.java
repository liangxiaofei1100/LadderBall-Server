package com.zhaoyan.ladderball.app.account;

import com.zhaoyan.ladderball.domain.account.http.AdministratorLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.AdministratorLoginResponse;
import com.zhaoyan.ladderball.service.account.AdministratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account/admin")
public class AdministratorController {
    Logger logger = LoggerFactory.getLogger(RecorderController.class);

    @Autowired
    AdministratorService administratorService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    AdministratorLoginResponse login(@RequestBody AdministratorLoginRequest request) {
        logger.debug("login() AdministratorLoginRequest: " + request);
        AdministratorLoginResponse response = administratorService.login(request);
        return response;
    }
}
