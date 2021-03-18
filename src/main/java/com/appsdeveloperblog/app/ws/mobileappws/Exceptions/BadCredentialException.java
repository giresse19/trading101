package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class BadCredentialException extends BadCredentialsException {
    public BadCredentialException(String msg) {
        super(msg);
    }
}
