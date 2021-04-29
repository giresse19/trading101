package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<AddressRest> addresses;

}
