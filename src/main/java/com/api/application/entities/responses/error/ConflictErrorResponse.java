package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class ConflictErrorResponse extends ErrorResponse {

    private ConflictErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<ConflictErrorResponse, ConflictErrorResponse.Builder> {
        private Integer status = HttpStatus.CONFLICT.value();
        private String message;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ConflictErrorResponse build() {
            return new ConflictErrorResponse(
                    status,
                    message
            );
        }
    }

    public static Builder builder() {
        return new ConflictErrorResponse.Builder();
    }
}
