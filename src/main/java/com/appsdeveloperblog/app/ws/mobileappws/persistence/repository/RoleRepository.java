package com.appsdeveloperblog.app.ws.mobileappws.persistence.repository;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String Name);
}
