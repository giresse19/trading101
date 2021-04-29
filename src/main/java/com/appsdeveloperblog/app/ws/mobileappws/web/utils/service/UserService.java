package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service;

import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService <T extends UserDto> extends UserDetailsService {
     UserDto createUser(final T resource);
     UserDto getUser(String email);
     UserDto getUserByUserId(String id);
     UserDto updateUser(String id, UserDto user);
     List<UserDto> getUsers(int page, int limit);
     void deleteUser(String id);

}
