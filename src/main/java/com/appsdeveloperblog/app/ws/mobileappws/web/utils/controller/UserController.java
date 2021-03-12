package com.appsdeveloperblog.app.ws.mobileappws.web.utils.controller;

import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.OperationStatusModel;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.RequestOperationName;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.RequestOperationStatus;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.UserRest;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.JwtUtils;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.appsdeveloperblog.app.ws.mobileappws.web.utils.QueryConstants.*;

@RestController
@RequestMapping("api/users")
public class UserController extends AbstractController<UserDto>{
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest createUser(@Valid @RequestBody final UserDto userDetails) {
        UserRest returnValue = new UserRest();
        UserDto createdUser = createInternal(userDetails);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping(
            path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {

        UserRest returnValue = new UserRest();

        //initialize the userDto
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(
            @RequestParam(value = PAGE, defaultValue = DEFAULT_START) int page,
            @RequestParam(value = LIMIT, defaultValue = DEFAULT_LIMIT) int limit
    ) {
        List<UserRest> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto userdto : users) {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userdto, userRest);
            returnValue.add(userRest);
        }

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
