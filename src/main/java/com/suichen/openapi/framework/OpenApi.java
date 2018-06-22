package com.suichen.openapi.framework;

import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OpenApi {
    OpenApiType value();
    HttpMethod method() default HttpMethod.GET;
}
