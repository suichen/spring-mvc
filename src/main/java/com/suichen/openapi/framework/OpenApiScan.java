package com.suichen.openapi.framework;

import com.suichen.openapi.common.CommonUtils;
import com.suichen.openapi.support.AbstractScanProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class OpenApiScan extends AbstractScanProvider {

    private static final String DEFAULT_OPEN_API_PACKAGE="com.suichen.openapi.api";

    @Override
    public String getBasePackage(String basePackage) {
        return DEFAULT_OPEN_API_PACKAGE;
    }

    @Override
    public boolean filter(MetadataReader metadataReader) {
        return metadataReader.getAnnotationMetadata().hasAnnotatedMethods(OpenApi.class.getName());
    }

    @PostConstruct
    public void do$Scan() {
        List<MetadataReader> metadataReaders = doScan(DEFAULT_OPEN_API_PACKAGE);

        metadataReaders.stream().forEach(metadataReader -> {
           String className = metadataReader.getClassMetadata().getClassName();
            Class clazz = CommonUtils.loadClass(className);

            for (Method method:clazz.getDeclaredMethods()) {
                if (method.getAnnotation(OpenApi.class)!=null) {
                    OpenApi openApi = method.getAnnotation(OpenApi.class);
                    OpenApiType openApiType = openApi.value();
                    HttpMethod httpMethod = openApi.method();

                    OpenApiMethodInfo methodInfo = new OpenApiMethodInfo();
                    methodInfo.setClazz(clazz);
                    methodInfo.setMethod(method);
                    methodInfo.setHttpMethod(httpMethod);

                    OpenApiRegistry.registry(openApiType, methodInfo);
                }
            }
        });
    }

    public void doScan1() {
        doScan(DEFAULT_OPEN_API_PACKAGE).stream().map(metadataReader -> {
             ReflectionUtils.doWithMethods(CommonUtils.loadClass(metadataReader.getClassMetadata().getClassName()),
                this::registry, this::filterAnnotation);
             return null;
        });
    }

    private boolean filterAnnotation(Method method) {
        return method!=null && method.getAnnotation(OpenApi.class) != null;
    }

    public void registry(Method method) {

    }
}
