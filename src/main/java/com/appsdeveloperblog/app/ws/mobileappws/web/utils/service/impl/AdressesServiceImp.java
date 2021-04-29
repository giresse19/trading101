package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Address;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.AddressesRepository;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.AddressesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdressesServiceImp implements AddressesService<AddressDto> {
    private final UserRepository userRepository;
    private final AddressesRepository addressesRepository;

    @Autowired
    public AdressesServiceImp(UserRepository userRepository,  AddressesRepository addressesRepository) {
        this.userRepository = userRepository;
        this.addressesRepository =  addressesRepository;
    }

    @Override
    public List<AddressDto> getAddresses(String id) {
        ModelMapper modelMapper = new ModelMapper();
        List<AddressDto> addresses = new ArrayList<>();
        User user = userRepository.findByUserId(id);

        for (int i = 0; i<user.getAddresses().size(); i++){
            Address address = user.getAddresses().get(i);
            AddressDto addressDto = modelMapper.map(address, AddressDto.class);
            addresses.add(addressDto);
        }

        return addresses;
    }

    @Override
    public AddressDto getUserAddress(String addressId) {
        Address address = addressesRepository.findByAddressId(addressId);
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);

//        return new ModelMapper().map(address, AddressDto.class);
        return  addressDto;
    }
}
