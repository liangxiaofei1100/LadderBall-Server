package com.zhaoyan.ladderball.management.account;

import com.zhaoyan.ladderball.domain.account.http.RecorderAddRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderAddResponse;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginRequest;
import com.zhaoyan.ladderball.domain.account.http.RecorderLoginResponse;
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

    /**
     * 添加记录员
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    RecorderAddResponse addRecorder(@RequestBody RecorderAddRequest request) {
        logger.debug("addRecorder() RecorderAddRequest: " + request);
        RecorderAddResponse response = recorderService.addRecorder(request);
        return response;
    }
}
