package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.RestPreconditions;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;

public abstract class AbstractController <T extends INameableDto> extends AbstractReadOnlyController<T>{

    // save/create/persist
    protected final UserDto createInternal(final UserDto resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
      //  RestPreconditions.checkRequestState(resource.getId() == null);
        return getUserService().createUser(resource);
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
//    protected final UserDto updateInternal(final String id, final T resource) {
//        RestPreconditions.checkRequestElementNotNull(resource);
//        RestPreconditions.checkRequestElementNotNull(resource.getId());
//        RestPreconditions.checkIfBadRequest(resource.getId() == id, resource.getClass()
//                .getSimpleName() + " id and URI id don't match");
//        RestPreconditions.checkNotNull(getUserService().findOne(resource.getId()));
//
//       return getUserService().updateUser(id, resource);
//    }
}
