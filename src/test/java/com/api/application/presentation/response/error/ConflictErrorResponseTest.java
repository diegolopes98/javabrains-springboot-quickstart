package com.api.application.presentation.response.error;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConflictErrorResponseTest {
    final int ERROR_HTTP_STATUS = HttpStatus.CONFLICT.value();
    final String ERROR_MESSAGE = "Conflict Error!";

    @Test
    void shouldBuildCorrectly() {
        ConflictErrorResponse errorResponse = ConflictErrorResponse.builder().build();
        assertEquals(ERROR_HTTP_STATUS, errorResponse.getStatus());
        assertEquals(ERROR_MESSAGE, errorResponse.getMessage());
    }

    @Test
    void checkResponseEntity() {
        ConflictErrorResponse errorResponse = ConflictErrorResponse.builder().build();

        ResponseEntity responseEntity = errorResponse.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
        assertEquals(responseEntity.getBody(), errorResponse);
    }
}
