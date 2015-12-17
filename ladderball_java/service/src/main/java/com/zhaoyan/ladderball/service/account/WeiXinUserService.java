package com.zhaoyan.ladderball.service.account;

import com.zhaoyan.ladderball.dao.account.WeiXinUserDao;
import com.zhaoyan.ladderball.domain.account.db.WeiXinUser;
import com.zhaoyan.ladderball.domain.account.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeiXinUserService {
    Logger logger = LoggerFactory.getLogger(WeiXinUserService.class);

    @Autowired
    @Qualifier("hibernateWeiXinUserDao")
    WeiXinUserDao weiXinUserDao;


    /**
     * 添加一个微信用户
     */
    public WeiXinUserAddResponse addWeiXinUser(WeiXinUserAddRequest request) {
        WeiXinUserAddResponse response = new WeiXinUserAddResponse();

        WeiXinUser user = weiXinUserDao.getWeiXinUserByWeiXinId(request.weiXinId);
        if (user != null) {
            response.buildFail("该用户已经添加过");
            return response;
        }
        user = new WeiXinUser();
        user.weiXinId = request.weiXinId;
        user.createTime = new Date();
        user.lastLoginTime = new Date();
        weiXinUserDao.addWeiXinUser(user);

        response.buildOk();
        return response;
    }

    private static BeanCopier copierWeiXinUserToWeiXinUserInfoResponse =
            BeanCopier.create(WeiXinUser.class, WeiXinUserInfoResponse.class, false);

    /**
     * 获取用户信息
     */
    public WeiXinUserInfoResponse getWeiXinUserInfo(WeiXinUserInfoRequest request) {
        WeiXinUserInfoResponse response = new WeiXinUserInfoResponse();

        WeiXinUser user = weiXinUserDao.getWeiXinUserByWeiXinId(request.weiXinId);
        if (user == null) {
            response.buildFail("无法找到该用户");
            return response;
        }

        copierWeiXinUserToWeiXinUserInfoResponse.copy(user, response, null);
        if (user.birthday != null) {
            response.birthday = user.birthday.getTime();
        }

        response.buildOk();
        return response;
    }
}
