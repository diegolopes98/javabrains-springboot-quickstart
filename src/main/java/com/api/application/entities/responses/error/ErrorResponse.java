package com.api.application.entities.responses.error;

import com.api.application.entities.interfaces.response.ResponseInterface;
import org.springframework.http.ResponseEntity;

abstract public class ErrorResponse implements ResponseInterface {
    protected Integer status;
    protected String message;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public ResponseEntity toResponse() {
        return ResponseEntity
                .status(this.status)
                .body(this);
    }
}
