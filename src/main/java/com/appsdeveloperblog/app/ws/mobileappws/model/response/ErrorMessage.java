package com.appsdeveloperblog.app.ws.mobileappws.model.response;

import java.util.Date;

@lombok.Data
public class ErrorMessage {
    private Date timestamp;
    private String message;

    public ErrorMessage(Date timestamp, String message ) {
        this.timestamp = timestamp;
        this.message = message;

    }
}
