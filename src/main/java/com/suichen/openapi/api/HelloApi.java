package com.suichen.openapi.api;

import com.suichen.openapi.framework.OpenApi;
import com.suichen.openapi.framework.OpenApiParam;
import com.suichen.openapi.framework.OpenApiType;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {
    @OpenApi(OpenApiType.helloOpenApi)
    public String helloApi(@OpenApiParam(value = "name") String myName) {
        System.out.println("SUCCESS "+myName);
        return "SUCCESS "+myName;
    }

}
