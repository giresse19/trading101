package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.IWithName;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractReadOnlyController <T extends IWithName & INameableDto> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract UserService getUserService();

}
