package com.zhaoyan.ladderball.domain.common.http;

public class RequestHeader {
    /**
     * 用户id
     */
    public String userToken;
    /**
     * app版本号
     */
    public String clientVersion;
    /**
     * 服务版本号
     */
    public int serviceVersion;
    /**
     * 客户端时间
     */
    public long requestTime;

    @Override
    public String toString() {
        return "RequestHeader{" +
                "userToken='" + userToken + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", serviceVersion=" + serviceVersion +
                ", requestTime=" + requestTime +
                '}';
    }
}
