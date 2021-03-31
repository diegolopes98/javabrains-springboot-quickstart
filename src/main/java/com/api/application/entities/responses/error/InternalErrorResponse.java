package com.api.application.entities.responses.error;

import org.springframework.http.HttpStatus;

public class InternalErrorResponse extends ErrorResponse {

    public InternalErrorResponse() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = "Internal Server Error! Please contact an Administrator.";
    }

    public InternalErrorResponse(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }
}
