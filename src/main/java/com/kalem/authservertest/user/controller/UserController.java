package com.kalem.authservertest.user.controller;

import com.kalem.authservertest.user.model.UserDto;
import com.kalem.authservertest.user.service.UserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rayan Aksu
 * @since 7/30/2023
 */

@RestController
//@RequestMapping( value = "/user" )
@RequiredArgsConstructor
public class UserController {
    private final UserCrudService userCrudService;

    @RequestMapping( "/user" )
    public UserDto getUserDetailsAfterLogin( Authentication authentication ) {
        UserDto user = userCrudService.findFirstByUsername( authentication.getName() );
        if ( user != null ) {
            return user;
        } else {
            return null;
        }

    }

    @PostMapping( "/register" )
    public ResponseEntity<String> registerUser( @RequestBody UserDto user ) {
        ResponseEntity response = null;
        try {
            UserDto savedUser = userCrudService.create( user );
            if ( savedUser.getId() > 0 ) {
                response = ResponseEntity
                        .status( HttpStatus.CREATED )
                        .body( "Given user details are successfully registered" );
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( "An exception occured due to " + ex.getMessage() );
        }
        return response;
    }

}
