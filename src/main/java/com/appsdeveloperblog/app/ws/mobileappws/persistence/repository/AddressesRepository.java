package com.appsdeveloperblog.app.ws.mobileappws.persistence.repository;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesRepository extends CrudRepository<Address, Long> {
    Address findByAddressId(String addressId);
}
