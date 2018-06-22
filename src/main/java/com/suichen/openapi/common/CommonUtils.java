package com.suichen.openapi.common;

import org.springframework.util.ClassUtils;

public class CommonUtils {
    public static Class<?> loadClass(String className) {
        try {
            return ClassUtils.forName(className, CommonUtils.class.getClassLoader());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object loadObject(Class<?> clazz) {
        try {
            return clazz.newInstance();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
