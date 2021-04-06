package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class NotFoundErrorResponse extends ErrorResponse {
    public static class Builder extends ErrorResponse.Builder<NotFoundErrorResponse, NotFoundErrorResponse.Builder> {
        @Override
        protected void setThisObject() {
            NotFoundErrorResponse notFoundErrorResponse = new NotFoundErrorResponse();
            notFoundErrorResponse
                    .setStatus(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found resources with given information!");
            thisObject = notFoundErrorResponse;
        }

        @Override
        protected Builder getThisBuilder() {
            return this;
        }
    }

    private NotFoundErrorResponse() {
    }
}
