package com.zhaoyan.ladderball.domain.account.http;


import com.zhaoyan.ladderball.domain.common.http.Request;

public class RecorderSetPasswordRequest extends Request{
    public String password;
    public String newPassword;

    @Override
    public String toString() {
        return "RecorderSetPasswordRequest{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
