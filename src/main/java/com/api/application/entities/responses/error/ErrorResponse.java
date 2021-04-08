package com.api.application.entities.responses.error;

import com.api.application.entities.interfaces.response.ResponseInterface;
import org.springframework.http.ResponseEntity;

abstract public class ErrorResponse implements ResponseInterface {
    private Integer status;
    private String message;

    public static abstract class Builder <T extends ErrorResponse, B extends Builder<T, B>> {
        abstract ErrorResponse build();
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    protected ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public ResponseEntity toResponse() {
        return ResponseEntity
                .status(this.status)
                .body(this);
    }
}
