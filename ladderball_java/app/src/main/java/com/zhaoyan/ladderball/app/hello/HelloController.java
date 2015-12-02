package com.zhaoyan.ladderball.app.hello;

import com.zhaoyan.ladderball.domain.hello.HelloRequest;
import com.zhaoyan.ladderball.domain.hello.HelloResponse;
import com.zhaoyan.ladderball.service.hello.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    HelloService helloService;

    /**
     * Say a hello to test.
     */
    @RequestMapping(value = "/hello", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    HelloResponse sayHello(@RequestBody HelloRequest request) {
        logger.debug("sayHello() HelloRequest: " + request);
        HelloResponse response = helloService.sayHelloRequest();
        logger.debug("sayHello() HelloResponse: " + response);
        return response;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    @RequestMapping(value = "/", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String home() {
        String helloMessage = helloService.sayHelloHome();

        String message = "欢迎来到App主页，当前时间：" + format.format(new Date());
        message += "\n";
        message += helloMessage;

        return message;
    }

}
