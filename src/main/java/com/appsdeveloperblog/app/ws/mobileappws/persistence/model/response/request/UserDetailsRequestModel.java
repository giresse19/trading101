package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request;

import lombok.Data;

@Data
public class UserDetailsRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
