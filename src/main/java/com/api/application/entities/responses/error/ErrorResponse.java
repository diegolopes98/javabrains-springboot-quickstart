package com.api.application.entities.responses.error;

import com.api.application.entities.interfaces.response.ResponseInterface;
import org.springframework.http.ResponseEntity;

abstract public class ErrorResponse implements ResponseInterface {
    protected Integer status;
    protected String message;

    protected static abstract class Builder<T extends ErrorResponse, B extends Builder<T, B>> {
        protected T thisObject;
        protected B thisBuilder;

        protected abstract void setThisObject();
        protected abstract B getThisBuilder();

        public Builder() {
            setThisObject();
            thisBuilder = getThisBuilder();
        }

        public B setMessage(String message) {
            thisObject.setMessage(message);
            return thisBuilder.getThisBuilder();
        }

        public T build() {
            return thisObject;
        }
    }

    protected ErrorResponse() {}

    public Integer getStatus() {
        return status;
    }

    protected ErrorResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public ResponseEntity toResponse() {
        return ResponseEntity
                .status(this.status)
                .body(this);
    }
}
