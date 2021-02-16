package com.appsdeveloperblog.app.ws.mobileappws.service;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
     UserDto createUser(UserDto user);
     UserDto getUser(String email);
     UserDto getUserByUserId(String id);
}
