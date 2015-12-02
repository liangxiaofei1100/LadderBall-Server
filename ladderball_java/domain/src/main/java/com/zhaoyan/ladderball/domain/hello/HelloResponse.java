package com.zhaoyan.ladderball.domain.hello;


import com.zhaoyan.ladderball.domain.common.http.Response;

public class HelloResponse extends Response{
    public String hello = "Hello, It's me.";

    @Override
    public String toString() {
        return "HelloResponse{" +
                "hello='" + hello + '\'' +
                '}';
    }
}
