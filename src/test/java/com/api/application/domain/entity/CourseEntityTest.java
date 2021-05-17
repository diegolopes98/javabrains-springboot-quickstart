package com.api.application.domain.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseEntityTest {

    @Test
    public void checkForEntityAnnotations() {
        List<Class> courseAnnotations = new LinkedList<>(Arrays.asList(Entity.class, Table.class));

        AssertAnnotations.assertType(
                CourseEntity.class,
                courseAnnotations
        );
    }

    @Test
    public void checkForEntityTableName() {
        Table t = ReflectTool.getClassAnnotation(CourseEntity.class, Table.class);

        assertEquals("COURSES", t.name());
    }

    @Test
    public void checkForEntityFieldsAnnotations() {
        List<Class> idAnnotations = new LinkedList<>(Arrays.asList(Id.class));
        AssertAnnotations.assertField(
                CourseEntity.class,
                "id",
                idAnnotations
        );

        AssertAnnotations.assertField(
                CourseEntity.class,
                "name",
                new LinkedList<>()
        );

        AssertAnnotations.assertField(
                CourseEntity.class,
                "description",
                new LinkedList<>()
        );

        List<Class> topicRelationAnnotations = new LinkedList<>(Arrays.asList(ManyToOne.class));
        AssertAnnotations.assertField(
                CourseEntity.class,
                "topic",
                topicRelationAnnotations
        );
    }
    @Test
    public void checkForEntityGetterMethodsAnnotations() {
        AssertAnnotations.assertGetterMethod(
                CourseEntity.class,
                "getId",
                new LinkedList<Class>()
        );

        AssertAnnotations.assertGetterMethod(
                CourseEntity.class,
                "getName",
                new LinkedList<Class>()
        );

        AssertAnnotations.assertGetterMethod(
                CourseEntity.class,
                "getDescription",
                new LinkedList<Class>()
        );

        AssertAnnotations.assertGetterMethod(
                CourseEntity.class,
                "getTopic",
                new LinkedList<Class>()
        );
    }

    @Test
    public void checkForEntitySetterMethodsAnnotations() {
        AssertAnnotations.assertSetterMethod(
                CourseEntity.class,
                "setId",
                new LinkedList<Class>(),
                String.class
        );

        AssertAnnotations.assertSetterMethod(
                CourseEntity.class,
                "setName",
                new LinkedList<Class>(),
                String.class
        );

        AssertAnnotations.assertSetterMethod(
                CourseEntity.class,
                "setDescription",
                new LinkedList<Class>(),
                String.class
        );

        AssertAnnotations.assertSetterMethod(
                CourseEntity.class,
                "setTopic",
                new LinkedList<Class>(),
                TopicEntity.class
        );
    }


}
