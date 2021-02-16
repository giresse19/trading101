package com.appsdeveloperblog.app.ws.mobileappws.model.request;

import lombok.Data;

@Data
public class UserLoginRequestModel {
    private String email;
    private String password;

}
