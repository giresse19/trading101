package com.appsdeveloperblog.app.ws.mobileappws.web.utils.security;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Authority;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Role;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {
    private final User user;
    private String userId;

    public UserPrincipal(User user) {
        this.user = user;
        this.userId = user.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Collection<Authority> authorityEntities = new HashSet<>();

        //get user roles
        Collection<Role> roles = user.getRoles();

        if(roles == null) return authorities;

        roles.forEach((role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorityEntities.addAll(role.getAuthorities());
        }));

        authorityEntities.forEach((authority -> {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }));

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
       return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEmailVerificationStatus();
    }
}
