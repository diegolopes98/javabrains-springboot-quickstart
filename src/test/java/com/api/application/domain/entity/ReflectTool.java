package com.api.application.domain.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTool {
    public static <T extends Annotation> T getMethodAnnotation(
            Class<?> c,
            String methodName,
            Class<T> annotation
    ) {
        try {
            Method m = c.getDeclaredMethod(methodName);
            return (T) m.getAnnotation(annotation);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Annotation> T getFieldAnnotation(
            Class<?> c,
            String fieldName,
            Class<T> annotation
    ) {
        try {
            Field f = c.getDeclaredField(fieldName);
            return (T) f.getAnnotation(annotation);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Annotation> T getClassAnnotation(
            Class<?> c,
            Class<T> annotation
    ) {
        return (T) c.getAnnotation(annotation);
    }
}