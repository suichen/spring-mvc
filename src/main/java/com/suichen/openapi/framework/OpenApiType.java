package com.suichen.openapi.framework;

public enum OpenApiType {
    helloOpenApi("hello open api");

    String mark;

    OpenApiType(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public static String getOpenApiTypeName(OpenApiType type) {
        for (OpenApiType value:OpenApiType.values()) {
            if (value.name().equals(type.name())) {
                return value.name();
            }
        }
        return null;
    }
}
