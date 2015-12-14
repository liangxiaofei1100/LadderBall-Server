package com.zhaoyan.ladderball.domain.match.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class MatchListRequest extends Request{

    /**
     * 根据比赛是否完成筛选。
     */
    public int completeType;

    /**
     * 所有比赛
     */
    public static final int TYPE_ALL = 0;
    /**
     * 已完成
     */
    public static final int TYPE_COMPLETE = 1;
    /**
     * 未完车
     */
    public static final int TYPE_NOT_COMPLETE = 2;
}
