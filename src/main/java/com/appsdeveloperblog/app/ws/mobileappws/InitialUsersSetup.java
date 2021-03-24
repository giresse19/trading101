package com.appsdeveloperblog.app.ws.mobileappws;

import com.appsdeveloperblog.app.ws.mobileappws.dto.Utils;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Authority;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.Role;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.entity.User;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.AuthorityRepository;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.RoleRepository;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.web.utils.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Component
public class InitialUsersSetup {
private final AuthorityRepository authorityRepository;
private final UserRepository userRepository;
private final RoleRepository roleRepository;
private final Utils utils;
private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public InitialUsersSetup(AuthorityRepository authorityRepository, RoleRepository roleRepository, Utils utils,
                             BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository ) {
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
       Authority readAuthority = createAuthority(SecurityConstants.READ_AUTHORITY);
       Authority writeAuthority = createAuthority(SecurityConstants.WRITE_AUTHORITY);
       Authority deleteAuthority = createAuthority(SecurityConstants.DELETE_AUTHORITY);

       createRole(SecurityConstants.ROLE_USER, Arrays.asList(readAuthority, writeAuthority));
       Role roleAdmin =  createRole(SecurityConstants.ROLE_ADMIN, Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

       if(roleAdmin == null) return ;

       User adminUser = new User();
       adminUser.setFirstName("Giresse");
       adminUser.setLastName("bob");
       adminUser.setEmail(utils.generatedUserId(6) + "@gmail.com");
       adminUser.setFirstName("Giresse");
       adminUser.setEmailVerificationStatus(true);
       adminUser.setUserId(utils.generatedUserId(30));
       adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
       adminUser.setRoles(Arrays.asList(roleAdmin));
       userRepository.save(adminUser);
    }

    @Transactional
    Authority createAuthority(String name) {

        Authority authority = authorityRepository.findByName(name);

        if(authority == null) {
            authority = new Authority(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    @Transactional
    Role createRole(String name, Collection<Authority> authorities) {

        Role role = roleRepository.findByName(name);

        if(role == null) {
            role = new Role(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }


}
