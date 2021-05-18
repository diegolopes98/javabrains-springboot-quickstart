package com.api.application.presentation.response.error;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BadRequestErrorResponseTest {
    final int ERROR_HTTP_STATUS = HttpStatus.BAD_REQUEST.value();
    final String ERROR_MESSAGE = "Bad Request";

    @Test
    void shouldBuildCorrectly() {
        BadRequestErrorResponse errorResponse = BadRequestErrorResponse.builder().build();
        assertEquals(ERROR_HTTP_STATUS, errorResponse.getStatus());
        assertEquals(ERROR_MESSAGE, errorResponse.getMessage());
    }

    @Test
    void checkResponseEntity() {
        BadRequestErrorResponse errorResponse = BadRequestErrorResponse.builder().build();

        ResponseEntity responseEntity = errorResponse.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(responseEntity.getBody(), errorResponse);
    }
}
