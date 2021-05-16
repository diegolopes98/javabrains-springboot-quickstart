package com.api.application.presentation.response.error;

import org.springframework.http.HttpStatus;

public class InternalErrorResponse extends ErrorResponse {

    private InternalErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<InternalErrorResponse, InternalErrorResponse.Builder> {
        public InternalErrorResponse build() {
            return new InternalErrorResponse(
                    this.getStatus(),
                    this.getMessage()
            );
        }
    }

    public static Builder builder() {
        Builder builder = new InternalErrorResponse.Builder();
        builder
            .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .setMessage("Internal Server Error!");
        return builder;
    }
}
