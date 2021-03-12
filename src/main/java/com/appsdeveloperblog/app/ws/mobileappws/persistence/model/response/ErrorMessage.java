package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response;

import java.util.Date;

@lombok.Data
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private Throwable developerMessage;

    public ErrorMessage(Date timestamp, String message, Throwable developerMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.developerMessage = developerMessage;
    }
}
