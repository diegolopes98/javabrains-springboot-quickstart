package com.api.application.domain.entity;

import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseEntityTest {

    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    CourseModel courseModelMock = mock(CourseModel.class);
    TopicModel topicModelMock = mock(TopicModel.class);
    TopicEntity topicEntityMock = mock(TopicEntity.class);

    @BeforeEach
    void setUpMocks() {
        when(courseModelMock.getId()).thenReturn(TEST_ID);
        when(courseModelMock.getName()).thenReturn(TEST_NAME);
        when(courseModelMock.getDescription()).thenReturn(TEST_DESCRIPTION);
        when(courseModelMock.getTopic()).thenReturn(topicModelMock);
        reset(topicModelMock);
        reset(topicEntityMock);
    }

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

    @Test
    void checkEntityGetters() {
        CourseEntity courseEntity = new CourseEntity(TEST_ID, TEST_NAME, TEST_DESCRIPTION, topicEntityMock);

        assertEquals(TEST_ID, courseEntity.getId());
        assertEquals(TEST_NAME, courseEntity.getName());
        assertEquals(TEST_DESCRIPTION, courseEntity.getDescription());
        assertEquals(topicEntityMock, courseEntity.getTopic());
    }

    @Test
    void checkEntitySetters() {
        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId(TEST_ID);
        courseEntity.setName(TEST_NAME);
        courseEntity.setDescription(TEST_DESCRIPTION);
        courseEntity.setTopic(topicEntityMock);

        assertEquals(TEST_ID, courseEntity.getId());
        assertEquals(TEST_NAME, courseEntity.getName());
        assertEquals(TEST_DESCRIPTION, courseEntity.getDescription());
        assertEquals(topicEntityMock, courseEntity.getTopic());

        courseEntity.setId(null);
        assertEquals(TEST_ID, courseEntity.getId());
    }

    @Test
    void checkEntityInstantiatedByModel() {
        final String TOPIC_TEST_ID = "topic test id";
        final String TOPIC_TEST_NAME = "topic test name";
        final String TOPIC_TEST_DESCRIPTION = "topic test description";

        when(topicModelMock.getId()).thenReturn(TOPIC_TEST_ID);
        when(topicModelMock.getName()).thenReturn(TOPIC_TEST_NAME);
        when(topicModelMock.getDescription()).thenReturn(TOPIC_TEST_DESCRIPTION);

        CourseEntity courseEntity = new CourseEntity(courseModelMock);

        assertEquals(TEST_ID, courseEntity.getId());
        assertEquals(TEST_NAME, courseEntity.getName());
        assertEquals(TEST_DESCRIPTION, courseEntity.getDescription());

        assertEquals(TOPIC_TEST_ID, courseEntity.getTopic().getId());
        assertEquals(TOPIC_TEST_NAME, courseEntity.getTopic().getName());
        assertEquals(TOPIC_TEST_DESCRIPTION, courseEntity.getTopic().getDescription());
    }
}
