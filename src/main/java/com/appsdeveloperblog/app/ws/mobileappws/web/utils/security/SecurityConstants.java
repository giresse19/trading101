package com.appsdeveloperblog.app.ws.mobileappws.web.utils.security;

import com.appsdeveloperblog.app.ws.mobileappws.SpringApplicationContext;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Authority;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 300000;
    public static final long REFRESH_EXPIRATION_TIME = 3600000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_REFRESH_TOKEN = "isRefreshedToken";
    public static final String SIGN_UP_URL = "/users";
    public static final String ADMIN_DELETE_URL = "/users/**";
    public static final String READ_AUTHORITY = "READ_AUTHORITY";
    public static final String WRITE_AUTHORITY = "WRITE_AUTHORITY";
    public static final String DELETE_AUTHORITY = "DELETE_AUTHORITY";
    public static final String DELETE_ALL_AUTHORITY = "DELETE_AUTHORITY";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBeans("appProperties");
        return appProperties.getTokenSecret();
    }
}
