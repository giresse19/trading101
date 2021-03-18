package com.appsdeveloperblog.app.ws.mobileappws.persistence.repository;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(String userId);
}
