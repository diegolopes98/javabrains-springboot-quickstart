package com.api.application.entities.responses.error;

import com.api.application.entities.interfaces.response.ResponseInterface;
import org.springframework.http.ResponseEntity;

abstract public class ErrorResponse implements ResponseInterface {
    private Integer status;
    private String message;

    public static abstract class Builder <T extends ErrorResponse, B extends Builder<T, B>> {
        private String message;
        private Integer status;

        public String getMessage() {
            return message;
        }

        public Integer getStatus() {
            return status;
        }

        protected Builder<T, B> setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T, B> setMessage(String message) {
            this.message = message;
            return this;
        }

        public abstract ErrorResponse build();
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
