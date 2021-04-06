package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class ConflictErrorResponse extends ErrorResponse {
    public static class Builder extends ErrorResponse.Builder<ConflictErrorResponse, ConflictErrorResponse.Builder> {
        @Override
        protected void setThisObject() {
            ConflictErrorResponse conflictErrorResponse = new ConflictErrorResponse();
            conflictErrorResponse
                    .setStatus(HttpStatus.CONFLICT.value())
                    .setMessage("Conflict Error!");
            thisObject = conflictErrorResponse;
        }

        @Override
        protected Builder getThisBuilder() {
            return this;
        }
    }

    private ConflictErrorResponse() {
    }
}
