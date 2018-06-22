package com.suichen.openapi.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OpenApiRegistry {
    public static Map<OpenApiType, OpenApiMethodInfo> registry = new ConcurrentHashMap<>();

    public static void registry(OpenApiType type, OpenApiMethodInfo methodInfo) {
        registry.put(type, methodInfo);
    }

    private static boolean validType(String typeName) {
        for (OpenApiType apiType:OpenApiType.values()) {
            if (typeName.equals(apiType.name())) return true;
        }
        return false;
    }

    public static OpenApiMethodInfo get(OpenApiType type) {
        return registry.get(type);
    }

}
