package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptions extends AuthenticationException {
    public AuthenticationExceptions(String msg, Throwable cause) {
        super(msg, cause);
    }
}
