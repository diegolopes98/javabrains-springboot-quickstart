package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class InternalErrorResponse extends ErrorResponse {
    public static class Builder extends ErrorResponse.Builder<InternalErrorResponse, InternalErrorResponse.Builder> {
        @Override
        protected void setThisObject() {
            InternalErrorResponse internalErrorResponse = new InternalErrorResponse();
            internalErrorResponse
                    .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .setMessage("Internal Server Error!");
            thisObject = internalErrorResponse;
        }

        @Override
        protected Builder getThisBuilder() {
            return this;
        }
    }

    private InternalErrorResponse() {}
}
