package com.api.application.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CourseModelTest {

    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";
    final TopicModel topicModelMock = mock(TopicModel.class);

    @Test
    void checkModelGetters() {
        CourseModel model = new CourseModel(TEST_ID, TEST_NAME, TEST_DESCRIPTION, topicModelMock);

        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
        assertEquals(topicModelMock, model.getTopic());

    }

    @Test
    void checkModelSetters() {
        CourseModel model = new CourseModel();

        model.setId(TEST_ID);
        model.setName(TEST_NAME);
        model.setDescription(TEST_DESCRIPTION);
        model.setTopic(topicModelMock);

        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
        assertEquals(topicModelMock, model.getTopic());
    }
}
