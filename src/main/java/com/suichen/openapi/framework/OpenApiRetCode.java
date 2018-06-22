package com.suichen.openapi.framework;

public enum OpenApiRetCode {
    METHOD_NOT_EXIST("403", "method does not exist"),
    HTTP_TYPE_ERROR("405", "http method error"),
    BUSINESS_ERROR("504", "business error"),
    PARAMETER_ERROR("406", "parameter error");

    private String code;
    private String mark;

    OpenApiRetCode(String code, String mark) {
        this.code = code;
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
