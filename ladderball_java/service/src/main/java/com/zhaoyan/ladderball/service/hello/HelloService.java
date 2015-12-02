package com.zhaoyan.ladderball.service.hello;

import com.zhaoyan.ladderball.dao.hello.HelloDao;
import com.zhaoyan.ladderball.domain.hello.HelloResponse;
import com.zhaoyan.ladderball.domain.hello.db.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    HelloDao helloDao;

    public HelloResponse sayHelloRequest() {
        HelloResponse response = new HelloResponse();
        response.buildOk();
        return response;
    }

    public String sayHelloHome() {
        Greeting greeting =  helloDao.sayHello();
        return greeting.message;
    }
}
