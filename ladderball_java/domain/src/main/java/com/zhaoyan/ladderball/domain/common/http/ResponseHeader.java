package com.zhaoyan.ladderball.domain.common.http;

public class ResponseHeader {
    /**
     * 执行结果码
     */
    public int resultCode;
    /**
     * 执行结果文字
     */
    public String resultText;

    public static final int RESULT_CODE_OK = 0;
    public static final String RESULT_TEXT_OK_DEFAULT = "执行成功";
    public static final int RESULT_CODE_FAIL = 1;
    public static final String RESULT_TEXT_FAIL_DEFAULT = "执行失败";

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "resultCode=" + resultCode +
                ", resultText='" + resultText +
                '}';
    }
}
