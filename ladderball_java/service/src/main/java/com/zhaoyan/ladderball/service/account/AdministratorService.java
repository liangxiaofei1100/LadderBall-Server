package com.zhaoyan.ladderball.service.account;

import com.zhaoyan.ladderball.dao.account.AdministratorDao;
import com.zhaoyan.ladderball.domain.account.db.Administrator;
import com.zhaoyan.ladderball.domain.account.http.AdministratorLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.AdministratorLoginResponse;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    @Autowired
    @Qualifier("hibernateAdministratorDao")
    AdministratorDao administratorDao;

    public AdministratorLoginResponse login(AdministratorLoginRequest request) {
        AdministratorLoginResponse response = new AdministratorLoginResponse();

        Administrator administrator = null;
        int loginType = request.loginType;
        if (loginType == RecorderLoginRequest.LOGIN_TYPE_AUTO) {
            // 自动登录
            administrator = administratorDao.getAdministrator(request.userName);
        } else {
            // 密码登录
            administrator = administratorDao.getAdministrator(request.userName, request.password);
        }

        if (administrator != null) {
            // 登录验证成功
            response.buildOk();
            response.name = administrator.name;
            response.userToken = String.valueOf(administrator.id);
        } else {
            // 登录验证失败
            response.buildFail("账号或者密码错误");
        }

        return response;
    }
}

