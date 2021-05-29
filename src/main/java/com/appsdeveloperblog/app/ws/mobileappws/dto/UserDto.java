package com.appsdeveloperblog.app.ws.mobileappws.dto;

import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements INameableDto {
    private static final long serialVersionUID = 1L;

    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private List<AddressDto> addresses;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private Collection<String> roles ;

    @Override
    public String getName() {
        return null;
    }
}
