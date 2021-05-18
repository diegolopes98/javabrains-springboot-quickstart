package com.api.application.presentation.response.error;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NotFoundErrorResponseTest {
    final int ERROR_HTTP_STATUS = HttpStatus.NOT_FOUND.value();
    final String ERROR_MESSAGE = "Not Found with given information!";

    @Test
    void shouldBuildCorrectly() {
        NotFoundErrorResponse errorResponse = NotFoundErrorResponse.builder().build();
        assertEquals(ERROR_HTTP_STATUS, errorResponse.getStatus());
        assertEquals(ERROR_MESSAGE, errorResponse.getMessage());
    }

    @Test
    void checkResponseEntity() {
        NotFoundErrorResponse errorResponse = NotFoundErrorResponse.builder().build();

        ResponseEntity responseEntity = errorResponse.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(responseEntity.getBody(), errorResponse);
    }
}
