package com.appsdeveloperblog.app.ws.mobileappws.web.utils;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.BadCredentialException;
import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.JwtExceptions;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.security.SecurityConstants;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
@Data
public class JwtUtils {

    public boolean validateToken(String authToken, HttpServletRequest request) throws JwtExceptions, ExpiredJwtException {
        try {
            //jwt token has not been tampered with
            Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException exception) {
            throw new BadCredentialException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        } catch (ExpiredJwtException exception) {
            throw new JwtExceptions(ErrorMessages.JWT_EXPIRED.getErrorMessage(), exception);
        }
    }

    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

    }

}
