package com.suichen.openapi.framework;

import com.suichen.openapi.common.CommonUtils;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class OpenApiService {
    public Object call(String openApiType, Map<String, Object> requestParams, Map<String, Object> bodyRequest,
                       Map<String, Object> headerRequest, HttpServletRequest request) {

        try {
            OpenApiMethodInfo methodInfo = getMethodInfo(openApiType);
            checkHttpMethod(methodInfo, request);
            Object[] args = handleParameters(requestParams, bodyRequest, headerRequest, methodInfo);
            Method method = methodInfo.getMethod();
            return method.invoke(CommonUtils.loadObject(methodInfo.getClazz()), args);
        }catch (OpenApiException e) {
            throw new RuntimeException("Open Api Exception. "+e.getMark());
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void checkHttpMethod(OpenApiMethodInfo methodInfo, HttpServletRequest request) {
        Boolean equal = request.getMethod().equals(methodInfo.getHttpMethod().name());
        if (!equal) {
            throw new OpenApiException(request.getMethod()+" method is not support");
        }
    }
    private OpenApiMethodInfo getMethodInfo(String openApiType) {
        if (OpenApiType.getOpenApiTypeName(OpenApiType.valueOf(openApiType)) == null) {
            throw new OpenApiException(OpenApiRetCode.METHOD_NOT_EXIST);
        }

        return OpenApiRegistry.get(OpenApiType.valueOf(openApiType));
    }

    private Object[] handleParameters(Map<String, Object> requestParams,Map<String, Object> bodyParams,
                                      Map<String, Object> headerParams, OpenApiMethodInfo methodInfo) {

        Method method = methodInfo.getMethod();
        int parameterCount = method.getParameterCount();
        Object[] args = new Object[parameterCount];

        for (int paramIndex = 0; paramIndex < parameterCount; paramIndex++) {
            MethodParameter methodParameter = new MethodParameter(method, paramIndex);
            args[paramIndex] = handleSingleParameter(requestParams, bodyParams, headerParams, methodParameter);
        }

        return args;
    }

    private boolean checkRequired(OpenApiParam openApiParam, Object value) {
        return !openApiParam.required() || value != null;
    }

    private Object handleSingleParameter(Map<String, Object> requestParams, Map<String, Object> bodyRequest,
                                         Map<String, Object> headerParams, MethodParameter methodParameter) {

        OpenApiParam openApiParam = methodParameter.getParameterAnnotation(OpenApiParam.class);
        if (openApiParam == null) {
            throw new OpenApiException(OpenApiRetCode.PARAMETER_ERROR);
        }

        ParamOrigin origin = openApiParam.origin();

        Object value = null;

        String parameterName = methodParameter.getParameterName();
        parameterName = StringUtils.isEmpty(openApiParam.value()) ? parameterName : openApiParam.value();

        if (origin.name().equals(ParamOrigin.request.name())) {
            value = requestParams.get(parameterName);
        } else if (origin.name().equals(ParamOrigin.body.name())) {
            value = bodyRequest.get(parameterName);
        } else if (origin.name().equals(ParamOrigin.header.name())) {
            value = headerParams.get(parameterName);
        }

        if (!checkRequired(openApiParam,value)) {
            throw new OpenApiException("parameter "+openApiParam.value()+" need.");
        }
        if (value == null) return null;

        return typeConverter.convertIfNecessary(value, methodParameter.getParameterType(), methodParameter);
    }

    private SimpleTypeConverter typeConverter = new SimpleTypeConverter();

}
