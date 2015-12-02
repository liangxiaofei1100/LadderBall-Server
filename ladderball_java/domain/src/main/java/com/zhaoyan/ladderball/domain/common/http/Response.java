package com.zhaoyan.ladderball.domain.common.http;


public class Response {
    public ResponseHeader header;

    public void buildFail() {
        build(ResponseHeader.RESULT_CODE_FAIL, ResponseHeader.RESULT_TEXT_FAIL_DEFAULT);
    }

    public void buildFail(String resultText) {
        build(ResponseHeader.RESULT_CODE_FAIL, resultText);
    }

    public void buildOk() {
        build(ResponseHeader.RESULT_CODE_OK, ResponseHeader.RESULT_TEXT_OK_DEFAULT);
    }

    public void buildOk(String resultText) {
        build(ResponseHeader.RESULT_CODE_OK, resultText);
    }

    public void build(int resultCode, String resultText) {
        header = new ResponseHeader();
        header.resultCode = resultCode;
        header.resultText = resultText;
    }

    @Override
    public String toString() {
        return "Response{" +
                "header=" + header +
                '}';
    }
}
