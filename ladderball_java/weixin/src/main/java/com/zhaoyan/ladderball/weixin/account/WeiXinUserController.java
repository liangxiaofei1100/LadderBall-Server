package com.zhaoyan.ladderball.weixin.account;


import com.zhaoyan.ladderball.domain.account.http.*;
import com.zhaoyan.ladderball.service.account.WeiXinUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account/weixinuser")
public class WeiXinUserController {
    Logger logger = LoggerFactory.getLogger(WeiXinUserController.class);

    @Autowired
    WeiXinUserService weiXinUserService;


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    WeiXinUserAddResponse addWeiXinUser(@RequestBody WeiXinUserAddRequest request) {
        logger.debug("addWeiXinUser() WeiXinUserAddRequest: " + request);
        WeiXinUserAddResponse response = weiXinUserService.addWeiXinUser(request);
        return response;
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    WeiXinUserInfoResponse getWeiXinUserInfo(@RequestBody WeiXinUserInfoRequest request) {
        logger.debug("getWeiXinUserInfo() WeiXinUserInfoRequest: " + request);
        WeiXinUserInfoResponse response = weiXinUserService.getWeiXinUserInfo(request);
        return response;
    }
}

