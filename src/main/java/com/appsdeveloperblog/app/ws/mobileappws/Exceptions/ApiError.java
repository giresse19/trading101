package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ApiError {
    private int status;
    private String message;
    private Throwable developerMessage;
    private Date timestamp;

    public ApiError(Date timestamp, final int status, final String message, final Throwable developerMessage) {
        this.status = status;
        this.message = message;
        this.developerMessage = developerMessage;
        this.timestamp = timestamp;
    }
}
