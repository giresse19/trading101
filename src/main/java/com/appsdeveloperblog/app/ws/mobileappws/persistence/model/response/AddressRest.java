package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class AddressRest extends RepresentationModel<AddressRest> {
    private String AddressId;
    private String city;
    private String addressId;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
}
