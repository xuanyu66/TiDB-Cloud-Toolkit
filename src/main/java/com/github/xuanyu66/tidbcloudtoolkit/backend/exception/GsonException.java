package com.github.xuanyu66.tidbcloudtoolkit.backend.exception;

public class GsonException extends RuntimeException {

    public GsonException() {
        super();
    }

    public GsonException(String message) {
        super(message);
    }

    public GsonException(String message, Throwable cause) {
        super(message, cause);
    }
}