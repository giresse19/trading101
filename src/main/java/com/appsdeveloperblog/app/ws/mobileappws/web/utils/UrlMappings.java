package com.appsdeveloperblog.app.ws.mobileappws.web.utils;

public class UrlMappings {
    public static final String USERS = "/users";
    public static final String LOGIN = "/users/login";
    public static final String ADDRESSES = "/addresses";
    public static final String PRIVILEGES = "privileges";
    public static final String ROLES = "roles";

    // discoverability

    public static final class Singular {

        public static final String USER = "user";
        public static final String PRIVILEGE = "privilege";
        public static final String ROLE = "role";

    }

    private UrlMappings() {
        throw new AssertionError();
    }

    // API
}
