package com.tonghang.server.exception;

public class ServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5457896748397749957L;

    private int code;

    private int httpCode;

    public ServiceException(int code, int httpCode, String message,
            Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpCode = httpCode;
    }

    public ServiceException(int code, int httpCode, String message) {
        super(message);
        this.code = code;
        this.httpCode = httpCode;
    }

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
        this.httpCode = errorCode.getHttpCode();
    }

    public int getCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
