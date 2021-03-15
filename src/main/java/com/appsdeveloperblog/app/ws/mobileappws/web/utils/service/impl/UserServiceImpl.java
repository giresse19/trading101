package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.MyBadRequestException;
import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.dto.Utils;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Address;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        ModelMapper modelMapper = new ModelMapper();

        for (int i = 0; i<user.getAddresses().size(); i++){
            AddressDto address = user.getAddresses().get(i);
            address.setUserDetails(user);
            address.setAddressId(utils.generatedAddressId(30));
            user.getAddresses().set(i, address);
        }

        User userEntity = modelMapper.map(user, User.class);
        String publicUserId = utils.generatedUserId(30);

        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User storedUserDetails = userRepository.save(userEntity);

        return modelMapper.map(storedUserDetails, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        User fetchedUser = userRepository.findByEmail(email);
        UserDto user = new UserDto();
        BeanUtils.copyProperties(fetchedUser, user);
        return user;
    }

    @Override
    public UserDto getUserByUserId(String id) {
        User user = userRepository.findByUserId(id);
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String id, UserDto user) {
        User userEntity = userRepository.findByUserId(id);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> usersContent = new ArrayList<>();

        if (page > 0) page = page - 1;

        Pageable pageable = PageRequest.of(page, limit);
        Page<User> userEntityPage = userRepository.findAll(pageable);

        List<User> users = userEntityPage.getContent();

        for (User user : users) {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            usersContent.add(userDto);
        }
        return usersContent;
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findByUserId(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
                new ArrayList<>());
    }

}
