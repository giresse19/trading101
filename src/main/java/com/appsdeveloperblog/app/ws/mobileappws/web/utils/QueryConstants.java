package com.appsdeveloperblog.app.ws.mobileappws.web.utils;

public final class QueryConstants {
    public static final String QUESTIONMARK = "?";

    public static final String PAGE = "page";
    public static final String LIMIT = "limit";
    public static final String DEFAULT_START = "0";
    public static final String DEFAULT_LIMIT = "30";
    public static final String SIZE = "size";
    public static final String SORT_BY = "sortBy";
    public static final String SORT_ORDER = "sortOrder";

    public static final String ID = "id"; // is constant because it's used for the controller mapping

    private QueryConstants() {
        throw new AssertionError();
    }
}
