package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.*;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.JwtUtils;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.UrlMappings;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.AddressesService;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.appsdeveloperblog.app.ws.mobileappws.web.utils.QueryConstants.*;

@RestController
@RequestMapping(UrlMappings.USERS)
public class UserController extends AbstractController<UserDto>{
    private final UserService userService;
    private final AddressesService addressesService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService,  AddressesService addressesService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.addressesService = addressesService;
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public UserRest getUser(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = userService.getUserByUserId(id);
        return modelMapper.map(userDto, UserRest.class);
    }

    @GetMapping(path = "/{id}/addresses",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<AddressRest> getUserAddresses(@PathVariable String id) {

        List<AddressRest> addressesList = new ArrayList<>();
        List<AddressDto> addresses = addressesService.getAddresses(id);

        if(addresses != null && !addresses.isEmpty()){
            Type listType = new TypeToken<List<AddressRest>>() {}.getType();
            addressesList = new ModelMapper().map(addresses, listType);
        }
        return addressesList;

    }

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

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest createUser(@Valid @RequestBody final UserDetailsRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = createInternal(userDto);

        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping(
            path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest updateUser(@Valid @RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto updatedUser = userService.updateUser(id, userDto);

        return modelMapper.map(updatedUser, UserRest.class);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        userService.deleteUser(id);
        return returnValue;

    }

    @GetMapping(path = "/{id}/refreshtoken")
    public ResponseEntity<?>  refreshToken( HttpServletRequest request) throws Exception {
        //get claims fro http
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebTokenClaims(claims);
        String token = jwtUtils.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok("token successfully refreshed : " +  token);

    }

    public Map<String, Object> getMapFromIoJsonwebTokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    @Override
    protected UserService getUserService() {
        return  userService;
    }

}
