package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;


import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.AddressRest;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.UrlMappings;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.AddressesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMappings.ADDRESSES)
public class AddressesController {
    private final AddressesService<AddressDto> addressesService;

    @Autowired
    public AddressesController(AddressesService<AddressDto> addressesService) {
        this.addressesService = addressesService;
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public AddressRest getUserAddress(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = addressesService.getUserAddress(id);
        return modelMapper.map(addressDto, AddressRest.class);
    }


}
