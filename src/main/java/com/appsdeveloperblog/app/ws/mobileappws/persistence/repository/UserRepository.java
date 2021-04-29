package com.appsdeveloperblog.app.ws.mobileappws.persistence.repository;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(String userId);

    @Transactional
    @Modifying
    @Query("update users u set u.updatedAt = :updatedAt where u.userId = :userId")
    void updateLastLogin(@Param("updatedAt") Date updatedDate, @Param("userId") String userId);
}
