package com.zhaoyan.ladderball.service.account;


import com.zhaoyan.ladderball.dao.account.RecorderDao;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderSetPasswordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 修改信息
 */
@Service
public class RecorderModifyService {
    Logger logger = LoggerFactory.getLogger(RecorderModifyService.class);

    @Autowired
    @Qualifier("hibernateRecorderDao")
    RecorderDao recorderDao;

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
