package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.MyBadRequestException;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.INameableDto;
import com.appsdeveloperblog.app.ws.mobileappws.Interfaces.IWithName;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.service.IRawService;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller.AbstractController;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Utils utils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generatedUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String id) {
        User user = userRepository.findByUserId(id);

        if (user == null) throw new UsernameNotFoundException("User with ID " + id + "Not found");

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String id, UserDto user) {
        UserDto returnValue = new UserDto();
        User userEntity = userRepository.findByUserId(id);

        if (userEntity == null) throw new MyBadRequestException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);

        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        if (page > 0) page = page - 1;

        Pageable pageable = PageRequest.of(page, limit);
        Page<User> userEntityPage = userRepository.findAll(pageable);

        List<User> users = userEntityPage.getContent();

        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findByUserId(id);
        if (user == null) throw new MyBadRequestException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(user);
    }

    @Override
    public void checkIfUserExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new MyBadRequestException(ErrorMessages.EMAIL_ADDRESS_ALREADY_EXIST.getErrorMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
                new ArrayList<>());
    }

}
