package com.api.application.presentation.response.error;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InternalErrorResponseTest {
    final int ERROR_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR.value();
    final String ERROR_MESSAGE = "Internal Server Error!";

    @Test
    void shouldBuildCorrectly() {
        InternalErrorResponse errorResponse = InternalErrorResponse.builder().build();
        assertEquals(ERROR_HTTP_STATUS, errorResponse.getStatus());
        assertEquals(ERROR_MESSAGE, errorResponse.getMessage());
    }

    @Test
    void checkResponseEntity() {
        InternalErrorResponse errorResponse = InternalErrorResponse.builder().build();

        ResponseEntity responseEntity = errorResponse.toResponse();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity.getBody(), errorResponse);
    }

}
