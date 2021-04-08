package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class InternalErrorResponse extends ErrorResponse {

    private InternalErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<InternalErrorResponse, InternalErrorResponse.Builder> {
        private Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        private String message = "Internal server error!";

        public InternalErrorResponse build() {
            return new InternalErrorResponse(
                    status,
                    message
            );
        }
    }

    public static Builder builder() {
        return new InternalErrorResponse.Builder();
    }
}
