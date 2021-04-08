package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class ConflictErrorResponse extends ErrorResponse {

    private ConflictErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<ConflictErrorResponse, ConflictErrorResponse.Builder> {
        public ConflictErrorResponse build() {
            return new ConflictErrorResponse(
                    this.getStatus(),
                    this.getMessage()
            );
        }
    }

    public static Builder builder() {
        Builder builder = new ConflictErrorResponse.Builder();
        builder
            .setStatus(HttpStatus.CONFLICT.value())
            .setMessage("Conflict Error!");
        return builder;
    }
}
