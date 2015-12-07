package com.zhaoyan.ladderball.service.common;


public class BaseService {

    public long getRecorderIdFromUserToken(String userToken){
        try {
            return Long.valueOf(userToken);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0l;
    }
}
