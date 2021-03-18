package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service;

import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;

import java.util.List;

public interface AddressesService <T extends AddressDto>{
    List<AddressDto> getAddresses(String id);
    AddressDto getUserAddress(String addressId);
}
