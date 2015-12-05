package com.zhaoyan.ladderball.service.account;


import com.zhaoyan.ladderball.dao.account.RecorderDao;
import com.zhaoyan.ladderball.domain.account.db.Recorder;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginResponse;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * 录入数据人员
 */
@Service
public class RecorderService {
    Logger logger = LoggerFactory.getLogger(RecorderService.class);

    @Autowired
    @Qualifier("hibernateRecorderDao")
    RecorderDao recorderDao;

    // 属性复制工具，每创建一个BeanCopier大约要100ms，创建后调用则基本不花费时间
    private static BeanCopier copierRecorderToRecorderLoginResponse =
            BeanCopier.create(Recorder.class, RecorderLoginResponse.class, false);

    /**
     * 登录
     */
    public RecorderLoginResponse login(RecorderLoginRequest request) {
        Recorder recorder = null;

        int loginType = request.loginType;
        if (loginType == RecorderLoginRequest.LOGIN_TYPE_AUTO) {
            // 自动登录
            recorder = recorderDao.getRecorderByPhone(request.userName);
        } else {
            // 密码登录
            recorder = recorderDao.getRecorderByPhone(request.userName, request.password);
        }
        logger.debug("recorder: " + recorder);

        RecorderLoginResponse response = new RecorderLoginResponse();
        if (recorder != null) {
            // 登录验证成功
            response.buildOk();
            copierRecorderToRecorderLoginResponse.copy(recorder, response, null);
            response.userToken = String.valueOf(recorder.id);
        } else {
            // 登录验证失败
            response.buildFail("账号或者密码错误");
        }

        return response;
    }

    /**
     *  修改密码
     */
    public RecorderSetPasswordResponse setPassword(RecorderSetPasswordRequest request) {
        RecorderSetPasswordResponse response = new RecorderSetPasswordResponse();

        try {
            logger.debug("setPassword() request.header: " + request.header);
            long recorderId = Long.valueOf(request.header.userToken);
            boolean result = recorderDao.setPassword(recorderId, request.password, request.newPassword);
            if (result) {
                response.buildOk("修改密码成功");
            } else {
                response.buildFail("修改密码失败");
            }
        } catch (Exception e) {
            logger.error("setPassword() Exception: " + e);
            e.printStackTrace();
            response.buildFail("修改密码失败");
        }

        return response;
    }
}
