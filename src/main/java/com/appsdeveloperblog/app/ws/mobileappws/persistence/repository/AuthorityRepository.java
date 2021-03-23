package com.appsdeveloperblog.app.ws.mobileappws.persistence.repository;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(String name);
}
