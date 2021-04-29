package com.appsdeveloperblog.app.ws.mobileappws.dto;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements INameableDto {
    private long id;
    private String city;
    private String addressId;
    private String country;
    private String streetName;
    private String postalCode;
    private UserDto userDetails;

    @Override
    public String getName() {
        return null;
    }
}
