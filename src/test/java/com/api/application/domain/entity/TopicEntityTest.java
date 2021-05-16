package com.api.application.domain.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TopicEntityTest {

    @Test
    public void checkForEntityAnnotations() {
        List<Class> topicAnnotations = new LinkedList<>(Arrays.asList(Entity.class, Table.class));

        AssertAnnotations.assertType(
                TopicEntity.class,
                topicAnnotations
        );
    }

    @Test
    public void checkForEntityTableName() {
        Table t = ReflectTool.getClassAnnotation(TopicEntity.class, Table.class);

        assertEquals("TOPICS", t.name());
    }

    @Test
    public void checkForEntityFieldsAnnotations() {
        List<Class> idAnnotations = new LinkedList<>(Arrays.asList(Id.class));
        AssertAnnotations.assertField(
                TopicEntity.class,
                "id",
                idAnnotations
        );

        AssertAnnotations.assertField(
                TopicEntity.class,
                "name",
                new LinkedList<>()
        );

        AssertAnnotations.assertField(
                TopicEntity.class,
                "description",
                new LinkedList<>()
        );
    }

    @Test
    public void checkForEntityGetterMethodsAnnotations() {
        AssertAnnotations.assertGetterMethod(
                TopicEntity.class,
                "getId",
                new LinkedList<Class>()
        );

        AssertAnnotations.assertGetterMethod(
                TopicEntity.class,
                "getName",
                new LinkedList<Class>()
        );

        AssertAnnotations.assertGetterMethod(
                TopicEntity.class,
                "getDescription",
                new LinkedList<Class>()
        );
    }

    @Test
    public void checkForEntitySetterMethodsAnnotations() {
        AssertAnnotations.assertSetterMethod(
                TopicEntity.class,
                "setId",
                new LinkedList<Class>(),
                String.class
        );

        AssertAnnotations.assertSetterMethod(
                TopicEntity.class,
                "setName",
                new LinkedList<Class>(),
                String.class
        );

        AssertAnnotations.assertSetterMethod(
                TopicEntity.class,
                "setDescription",
                new LinkedList<Class>(),
                String.class
        );
    }
}
