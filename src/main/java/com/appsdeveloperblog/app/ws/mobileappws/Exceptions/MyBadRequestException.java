package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

public class MyBadRequestException extends RuntimeException{

    static final long serialVersionUID = -7034897190745766939L;

    public MyBadRequestException() {
        super();
    }
    public MyBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public MyBadRequestException(String message) {
        super(message);
    }
    public MyBadRequestException(final Throwable cause) {
        super(cause);
    }

}
