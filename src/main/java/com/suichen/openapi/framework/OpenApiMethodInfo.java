package com.suichen.openapi.framework;

import org.springframework.http.HttpMethod;

import java.lang.reflect.Method;

public class OpenApiMethodInfo {
    private Method method;
    private Class clazz;
    private HttpMethod httpMethod;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
