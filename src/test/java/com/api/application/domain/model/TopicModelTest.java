package com.api.application.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TopicModelTest {

    final String TEST_ID = "test id";
    final String TEST_NAME = "test name";
    final String TEST_DESCRIPTION = "test description";

    @Test
    void checkModelGetters() {
        TopicModel model = new TopicModel(TEST_ID, TEST_NAME, TEST_DESCRIPTION);

        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
    }

    @Test
    void checkModelSetters() {
        TopicModel model = new TopicModel();

        model.setId(TEST_ID);
        model.setName(TEST_NAME);
        model.setDescription(TEST_DESCRIPTION);

        assertEquals(TEST_ID, model.getId());
        assertEquals(TEST_NAME, model.getName());
        assertEquals(TEST_DESCRIPTION, model.getDescription());
    }
}
