package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.RestPreconditions;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;

public abstract class AbstractController <T extends INameableDto> extends AbstractReadOnlyController<T>{

    // save/create/persist
    protected final UserDto createInternal(final UserDto resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        return getUserService().createUser(resource);
    }

}
