package com.api.application.presentation.response.course;

import com.api.application.domain.model.CourseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseResponseTest {

    final String MOCK_MODEL_ID = "test id";
    final String MOCK_MODEL_NAME = "test name";
    final String MOCK_MODEL_DESCRIPTION = "test description";
    final CourseModel courseModelMock = mock(CourseModel.class);

    @BeforeEach
    void setUpMocks() {
        when(courseModelMock.getId()).thenReturn(MOCK_MODEL_ID);
        when(courseModelMock.getName()).thenReturn(MOCK_MODEL_NAME);
        when(courseModelMock.getDescription()).thenReturn(MOCK_MODEL_DESCRIPTION);
    }
    @Test
    void checkResponseGetters() {
        CourseResponse response1 = new CourseResponse(MOCK_MODEL_ID, MOCK_MODEL_NAME, MOCK_MODEL_DESCRIPTION);

        assertEquals(MOCK_MODEL_ID, response1.getId());
        assertEquals(MOCK_MODEL_NAME, response1.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response1.getDescription());

        CourseResponse response2 = new CourseResponse(courseModelMock);

        assertEquals(MOCK_MODEL_ID, response2.getId());
        assertEquals(MOCK_MODEL_NAME, response2.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response2.getDescription());
    }

    @Test
    void checkResponseSetters() {
        CourseResponse response = new CourseResponse();

        response.setId(MOCK_MODEL_ID);
        response.setName(MOCK_MODEL_NAME);
        response.setDescription(MOCK_MODEL_DESCRIPTION);

        assertEquals(MOCK_MODEL_ID, response.getId());
        assertEquals(MOCK_MODEL_NAME, response.getName());
        assertEquals(MOCK_MODEL_DESCRIPTION, response.getDescription());
    }

    @Test
    void checkResponseEntity() {
        CourseResponse response = new CourseResponse(MOCK_MODEL_ID, MOCK_MODEL_NAME, MOCK_MODEL_DESCRIPTION);

        ResponseEntity responseEntity = response.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), response);
    }
}
