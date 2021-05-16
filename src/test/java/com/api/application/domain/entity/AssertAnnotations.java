package com.api.application.domain.entity;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class AssertAnnotations {
    private static void assertAnnotations(
            List<Class> annotationClasses,
            List<Annotation> annotations
    ) {
        if (annotationClasses.size() != annotations.size()) {
            throw new AssertionError(
                    String.format(
                            "Expected %d annotations, but found %d",
                            annotationClasses.size(),
                            annotations.size()
                    )
            );
        }

        annotationClasses.forEach(
                aClass -> {
                    long count = annotations
                            .stream()
                            .filter(a -> a.annotationType().isAssignableFrom(aClass))
                            .count();
                    if (count == 0) {
                        throw new AssertionError(
                                String.format(
                                        "No annotation of type %s found",
                                        aClass.getName()
                                )
                        );
                    }
                }
        );
    }

    public static void assertType(Class c, List<Class> annotationsClasses) {
        assertAnnotations(
                Arrays.asList(annotationsClasses.toArray(new Class[annotationsClasses.size()])),
                Arrays.asList(c.getAnnotations())
        );
    }

    public static void assertField(Class c, String fieldName, List<Class> annotationsClasses) {
        try {
            assertAnnotations(
                    Arrays.asList(annotationsClasses.toArray(new Class[annotationsClasses.size()])),
                    Arrays.asList(c.getDeclaredField(fieldName).getAnnotations())
            );
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static void assertGetterMethod(Class c, String getterName, List<Class> annotationsClasses) {
        try {
            assertAnnotations(
                    Arrays.asList(annotationsClasses.toArray(new Class[annotationsClasses.size()])),
                    Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations())
            );
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    public static void assertSetterMethod(Class c, String setterName, List<Class> annotationsClasses, Class<?>... parameters) {
        try {
            assertAnnotations(
                    Arrays.asList(annotationsClasses.toArray(new Class[annotationsClasses.size()])),
                    Arrays.asList(c.getDeclaredMethod(setterName, parameters).getAnnotations())
            );
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }
}
