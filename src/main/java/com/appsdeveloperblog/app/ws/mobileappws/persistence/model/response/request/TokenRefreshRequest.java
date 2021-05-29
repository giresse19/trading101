package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

}
