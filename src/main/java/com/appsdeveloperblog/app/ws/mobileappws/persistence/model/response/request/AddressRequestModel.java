package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request;

import lombok.Data;

@Data
public class AddressRequestModel {
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
}
