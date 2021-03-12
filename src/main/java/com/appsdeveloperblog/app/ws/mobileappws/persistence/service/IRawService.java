package com.appsdeveloperblog.app.ws.mobileappws.persistence.service;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.IOperations;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import org.springframework.data.domain.Page;

public interface IRawService <T extends INameableDto> extends  IOperations<T> {

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);

    Page<T> findAllPaginatedRaw(final int page, final int size);
}
