package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request;

import lombok.Data;

@Data
public class UserLoginRequestModel {
    private String email;
    private String password;

}
