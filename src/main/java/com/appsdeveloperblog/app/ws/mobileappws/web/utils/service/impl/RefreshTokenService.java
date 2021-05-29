package com.appsdeveloperblog.app.ws.mobileappws.web.utils.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.Exceptions.TokenRefreshException;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.RefreshToken;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.RefreshTokenRepository;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserServiceImpl userService;

    @Autowired
    public RefreshTokenService(UserRepository userRepository,
                               RefreshTokenRepository refreshTokenRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByUserId(userId));
        refreshToken.setExpiryDate(Instant.now().plusMillis(SecurityConstants.REFRESH_EXPIRATION_TIME));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public void deleteByUserId(String userId) {
        userService.deleteUser(userId);
    }
}
