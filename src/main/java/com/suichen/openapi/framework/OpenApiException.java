package com.suichen.openapi.framework;

public class OpenApiException extends RuntimeException{
    private String code;
    private String mark;

    public OpenApiException(String code, String mark) {
        this.code = code;
        this.mark = mark;
    }

    public OpenApiException(OpenApiRetCode openApiRetCode) {
        this.code = openApiRetCode.getCode();
        this.mark = openApiRetCode.getMark();
    }

    public OpenApiException(String mark) {
        this.mark = mark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
