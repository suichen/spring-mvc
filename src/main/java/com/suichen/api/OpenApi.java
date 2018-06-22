package com.suichen.api;

import com.suichen.openapi.framework.OpenApiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class OpenApi {
    @RequestMapping("/suichen/{openApi}.json")
    public Object call(@PathVariable(value = "openApi") String openApi,
                       @RequestParam(required = false) Map<String, Object> requestParams,
                       @RequestBody(required = false) Map<String, Object> bodyParams,
                       @RequestHeader(required = false) Map<String, Object> headerParams,
                       HttpServletRequest request) {

        return openApiService.call(openApi, requestParams, bodyParams, headerParams, request);
    }

    @Resource
    private OpenApiService openApiService;
}
