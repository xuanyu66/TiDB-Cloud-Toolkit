package com.github.xuanyu66.tidbcloudtoolkit.backend.exception;

public class ToolkitException extends RuntimeException {

    public ToolkitException() {
        super();
    }

    public ToolkitException(String message) {
        super(message);
    }

    public ToolkitException(String message, Throwable cause) {
        super(message, cause);
    }
}
