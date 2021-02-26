package com.appsdeveloperblog.app.ws.mobileappws.service;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
     UserDto createUser(UserDto user);
     UserDto getUser(String email);
     UserDto getUserByUserId(String id);
     UserDto updateUser(String id, UserDto user);
    List<UserDto> getUsers(int page, int limit);
     void deleteUser(String id);
     void checkIfUserExist(String email);
}
