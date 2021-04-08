package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class NotFoundErrorResponse extends ErrorResponse {

    private NotFoundErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<NotFoundErrorResponse, NotFoundErrorResponse.Builder> {
        private Integer status = HttpStatus.NOT_FOUND.value();
        private String message = "Not found with given information!";

        public NotFoundErrorResponse build() {
            return new NotFoundErrorResponse(
                    status,
                    message
            );
        }
    }

    public static Builder builder() {
        return new NotFoundErrorResponse.Builder();
    }
}
