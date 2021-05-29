package com.appsdeveloperblog.app.ws.mobileappws.web.utils.security;

import com.appsdeveloperblog.app.ws.mobileappws.SpringApplicationContext;
import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.RefreshToken;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.request.UserLoginRequestModel;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.JwtUtils;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.UserService;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.impl.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter<refreshTokenService> extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try {

            //todo: Handle case were invalid username(email) exception
            UserLoginRequestModel cred = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail(),
                            cred.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void successfulAuthentication(HttpServletRequest req,
                                         HttpServletResponse res,
                                         FilterChain chain,
                                         Authentication auth) throws IOException, ServletException {

        UserService userService = (UserService) SpringApplicationContext.getBeans("userServiceImpl");
        RefreshTokenService refreshTokenService = (RefreshTokenService) SpringApplicationContext.getBeans("refreshTokenService");

        UserPrincipal userPrincipal = ((UserPrincipal) auth.getPrincipal());

        String token = jwtUtils.generateJwtToken(auth);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userPrincipal.getUserId());

        UserDto userDto = userService.getUser(userPrincipal.getUsername());

        res.addHeader(SecurityConstants.REFRESH_TOKEN, refreshToken.getToken());
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + " " + token);
        res.addHeader(SecurityConstants.USERID, userDto.getUserId());
        res.addHeader("ACCESS_TOKEN_EXPIRES_AT", String.valueOf(jwtUtils.getExpirationTimeFromJwtToken(token)));
        res.addHeader("REFRESH_TOKEN_EXPIRES_AT", String.valueOf(refreshToken.getExpiryDate()));

    }

}
