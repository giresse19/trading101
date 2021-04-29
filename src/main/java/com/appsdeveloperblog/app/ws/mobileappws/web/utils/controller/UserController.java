package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.*;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.JwtUtils;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.UrlMappings;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.security.SecurityConstants;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.AddressesService;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.*;

import static com.appsdeveloperblog.app.ws.mobileappws.web.utils.QueryConstants.*;

@RestController
@RequestMapping(UrlMappings.USERS)
public class UserController extends AbstractController<UserDto>{
    private final UserService<UserDto> userService;
    private final AddressesService<AddressDto> addressesService;
   // private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService<UserDto> userService,  AddressesService<AddressDto> addressesService, JwtUtils jwtUtils) {
        this.userService = userService;
     //   this.jwtUtils = jwtUtils;
        this.addressesService = addressesService;
    }

    // Get - A user
    @PostAuthorize("hasRole('ADMIN') or returnObject.userId == principal.userId")
    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public UserRest getUser(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = userService.getUserByUserId(id);
        return modelMapper.map(userDto, UserRest.class);
    }

    // Get - All addresses

    @GetMapping(path = "/{id}/addresses",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public CollectionModel<AddressRest> getUserAddresses(@PathVariable String id) {

        List<AddressRest> addressesList = new ArrayList<>();
        List<AddressDto> addresses = addressesService.getAddresses(id);

        if (addresses != null && !addresses.isEmpty()) {
            Type listType = new TypeToken<List<AddressRest>>() {
            }.getType();
            addressesList = new ModelMapper().map(addresses, listType);

            for (AddressRest addressRest : addressesList) {
                Link selfLink = WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserAddresses(id)).withSelfRel();

                addressRest.add(selfLink);
            }
        }

        Link userLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUser(id)).withRel("user");

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserAddresses(id)).withSelfRel();

        return CollectionModel.of(addressesList, userLink, selfLink);
    }

    // Get - A user address

    @GetMapping(path = "/{id}/addresses/{addressId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public EntityModel<AddressRest> getUserAddress(@PathVariable String id, @PathVariable String addressId) {
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = addressesService.getUserAddress(addressId);
        AddressRest addressRest = modelMapper.map(addressDto, AddressRest.class);

        Link userLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getUser(id))
                .withRel("user");

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getUserAddress(id, addressId))
                .withSelfRel();

        Link userAddressesLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserAddresses(id))
                .withRel("addresses");

        return EntityModel.of(addressRest, Arrays.asList(userLink, userAddressesLink, selfLink));
    }

    // Get - All Users

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(
            @RequestParam(value = PAGE, defaultValue = DEFAULT_START) int page,
            @RequestParam(value = LIMIT, defaultValue = DEFAULT_LIMIT) int limit
    ) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserRest> usersList = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto userdto : users) {
            UserRest userRest = modelMapper.map(userdto, UserRest.class);
            usersList.add(userRest);
        }

        return usersList;
    }

    // Created

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public UserRest createUser(@Valid @RequestBody final UserDetailsRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userDto.setRoles(new HashSet<>(Arrays.asList(SecurityConstants.ROLE_USER)));

        UserDto createdUser = createInternal(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    // Update

    @PutMapping(
            path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public UserRest updateUser(@Valid @RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto updatedUser = userService.updateUser(id, userDto);

        return modelMapper.map(updatedUser, UserRest.class);
    }

    // Delete
    @PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.userId")
   // @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        userService.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;

    }

    // Spring

    @Override
    protected final  UserService getUserService() {
        return  userService;
    }

}
