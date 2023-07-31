package com.kalem.authservertest.user.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 7/28/2023
 */


@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserCrudService userCrudService;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        var user = userCrudService.findFirstByUsername( username );
        userName = user.getUsername();
        password = user.getPassword();
        authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority( user.getRole() ) );
        return new User( userName, password, authorities );
    }
}
