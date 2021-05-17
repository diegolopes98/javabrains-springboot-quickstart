package com.api.application.domain.entity;

import com.api.application.domain.model.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TopicEntityTest {

    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";


    TopicModel topicMock = mock(TopicModel.class);

    @BeforeEach
    void setUpMocks() {
        when(topicMock.getId()).thenReturn(TEST_ID);
        when(topicMock.getName()).thenReturn(TEST_NAME);
        when(topicMock.getDescription()).thenReturn(TEST_DESCRIPTION);
    }

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

    @Test
    void checkEntityGetters() {
        TopicEntity topicEntity = new TopicEntity(TEST_ID, TEST_NAME, TEST_DESCRIPTION);

        assertEquals(TEST_ID, topicEntity.getId());
        assertEquals(TEST_NAME, topicEntity.getName());
        assertEquals(TEST_DESCRIPTION, topicEntity.getDescription());
    }

    @Test
    void checkEntitySetters() {
        TopicEntity topicEntity = new TopicEntity();

        topicEntity.setId(TEST_ID);
        topicEntity.setName(TEST_NAME);
        topicEntity.setDescription(TEST_DESCRIPTION);

        assertEquals(TEST_ID, topicEntity.getId());
        assertEquals(TEST_NAME, topicEntity.getName());
        assertEquals(TEST_DESCRIPTION, topicEntity.getDescription());

        topicEntity.setId(null);
        assertEquals(TEST_ID, topicEntity.getId());
    }

    @Test
    void checkEntityInstantiatedByModel() {
        TopicEntity topicEntity = new TopicEntity(topicMock);

        assertEquals(TEST_ID, topicEntity.getId());
        assertEquals(TEST_NAME, topicEntity.getName());
        assertEquals(TEST_DESCRIPTION, topicEntity.getDescription());
    }
}
