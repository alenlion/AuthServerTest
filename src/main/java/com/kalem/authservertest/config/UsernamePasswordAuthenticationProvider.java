package com.kalem.authservertest.config;

import com.kalem.authservertest.user.service.UserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 7/31/2023
 */

@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserCrudService userCrudService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        var user = userCrudService.findFirstByUsername( username );
        if ( user != null ) {
            if ( passwordEncoder.matches( pwd, user.getPassword() ) ) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add( new SimpleGrantedAuthority( user.getRole() ) );
                return new UsernamePasswordAuthenticationToken( username, pwd, authorities );
            } else {
                throw new BadCredentialsException( "Invalid password!" );
            }
        } else {
            throw new BadCredentialsException( "No user registered with this details!" );
        }
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom( authentication );

    }
}
