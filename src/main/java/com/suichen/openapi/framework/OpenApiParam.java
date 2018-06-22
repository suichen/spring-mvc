package com.suichen.openapi.framework;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface OpenApiParam {
    String value();
    ParamOrigin origin() default ParamOrigin.request;
    boolean required() default true;
}
