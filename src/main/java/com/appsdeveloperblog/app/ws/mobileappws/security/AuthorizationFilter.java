package com.appsdeveloperblog.app.ws.mobileappws.security;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.JwtExceptions;
import com.appsdeveloperblog.app.ws.mobileappws.SpringApplicationContext;
import com.appsdeveloperblog.app.ws.mobileappws.Utils.JwtUtils;
import com.appsdeveloperblog.app.ws.mobileappws.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        try {
            String token = request.getHeader(SecurityConstants.HEADER_STRING);

            if (token != null) {
                token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
                JwtUtils jwtUtils = (JwtUtils) SpringApplicationContext.getBeans("jwtUtils");
                Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token);

                UserDetails user = new User(jwtUtils.getUserNameFromToken(token), "", new ArrayList<>());
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        } catch (ExpiredJwtException ex) {
            String isRefreshToken = request.getHeader(SecurityConstants.HEADER_REFRESH_TOKEN);
            String requestURL = request.getRequestURI();

            if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
                allowForRefreshToken(ex, request);
            } else {
                request.setAttribute("Exception", ex);
            }
        } catch (BadCredentialsException ex) {
            request.setAttribute("Exception", ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        // create a UsernamePasswordAuthenticationToken with null values.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // Set the claims so that in controller we will be using it to create new JWT
        request.setAttribute("claims", ex.getClaims());
    }
}
