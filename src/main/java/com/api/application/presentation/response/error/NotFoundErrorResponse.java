package com.api.application.presentation.response.error;

import org.springframework.http.HttpStatus;

public class NotFoundErrorResponse extends ErrorResponse {

    private NotFoundErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<NotFoundErrorResponse, NotFoundErrorResponse.Builder> {
        public NotFoundErrorResponse build() {
            return new NotFoundErrorResponse(
                    this.getStatus(),
                    this.getMessage()

            );
        }
    }

    public static Builder builder() {
        Builder builder = new NotFoundErrorResponse.Builder();
        builder
            .setMessage("Not Found with given information!")
            .setStatus(HttpStatus.NOT_FOUND.value());
        return builder;
    }
}
