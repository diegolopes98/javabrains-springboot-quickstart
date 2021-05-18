package com.api.application.presentation.response.topic;

import com.api.application.domain.model.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TopicResponseTest {
    final String MOCK_MODEL_ID = "test id";
    final String MOCK_MODEL_NAME = "test name";
    final String MOCK_MODEL_DESCRIPTION = "test description";
    final TopicModel topicModelMock = mock(TopicModel.class);

    @BeforeEach
    void setUpMocks() {
        when(topicModelMock.getId()).thenReturn(MOCK_MODEL_ID);
        when(topicModelMock.getName()).thenReturn(MOCK_MODEL_NAME);
        when(topicModelMock.getDescription()).thenReturn(MOCK_MODEL_DESCRIPTION);
    }
    @Test
    void checkResponseGetters() {
        TopicResponse response1 = new TopicResponse(MOCK_MODEL_ID, MOCK_MODEL_NAME, MOCK_MODEL_DESCRIPTION);

        assertEquals(MOCK_MODEL_ID, response1.getId());
        assertEquals(MOCK_MODEL_NAME, response1.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response1.getDescription());

        TopicResponse response2 = new TopicResponse(topicModelMock);

        assertEquals(MOCK_MODEL_ID, response2.getId());
        assertEquals(MOCK_MODEL_NAME, response2.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response2.getDescription());
    }

    @Test
    void checkResponseSetters() {
        TopicResponse response = new TopicResponse();

        response.setId(MOCK_MODEL_ID);
        response.setName(MOCK_MODEL_NAME);
        response.setDescription(MOCK_MODEL_DESCRIPTION);

        assertEquals(MOCK_MODEL_ID, response.getId());
        assertEquals(MOCK_MODEL_NAME, response.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response.getDescription());
    }

    @Test
    void checkResponseEntity() {
        TopicResponse response = new TopicResponse(MOCK_MODEL_ID, MOCK_MODEL_NAME, MOCK_MODEL_DESCRIPTION);

        ResponseEntity responseEntity = response.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), response);
    }

}
