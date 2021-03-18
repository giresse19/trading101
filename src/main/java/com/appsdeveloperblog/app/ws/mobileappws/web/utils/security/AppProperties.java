package com.appsdeveloperblog.app.ws.mobileappws.web.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    private final Environment env;

    @Autowired
        public AppProperties(Environment env) {
        this.env = env;
    }

    public String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }
}
