package com.api.application.presentation.response.error;

import org.springframework.http.HttpStatus;

public class BadRequestErrorResponse extends ErrorResponse {

    private BadRequestErrorResponse(Integer status, String message) {
        super(status, message);
    }

    public static class Builder extends ErrorResponse.Builder<BadRequestErrorResponse, BadRequestErrorResponse.Builder> {
        public BadRequestErrorResponse build() {
            return new BadRequestErrorResponse(
                    this.getStatus(),
                    this.getMessage()
            );
        }
    }

    public static BadRequestErrorResponse.Builder builder() {
        BadRequestErrorResponse.Builder builder = new BadRequestErrorResponse.Builder();
        builder
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage("Bad Request");
        return builder;
    }
}
