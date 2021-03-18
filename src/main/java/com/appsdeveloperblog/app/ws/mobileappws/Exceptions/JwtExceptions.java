package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class JwtExceptions extends Exception{

    public JwtExceptions(String message, Exception ex) {
        super(message, ex);
    }
}
