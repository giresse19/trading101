package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.MyResourceNotFoundException;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.IWithName;
import com.appsdeveloperblog.app.ws.mobileappws.RestPreconditions;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.service.IRawService;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractReadOnlyController <T extends IWithName & INameableDto> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract UserService getUserService();

}
