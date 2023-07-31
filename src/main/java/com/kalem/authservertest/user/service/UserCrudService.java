package com.kalem.authservertest.user.service;

import com.kalem.authservertest.baseclass.service.BaseCrudService;
import com.kalem.authservertest.user.mapper.UserMapper;
import com.kalem.authservertest.user.model.UserDto;
import com.kalem.authservertest.user.model.UserEntity;
import com.kalem.authservertest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Rayan Aksu
 * @since 7/27/2023
 */

@RequiredArgsConstructor
@Service
public class UserCrudService implements BaseCrudService<Integer, UserEntity, UserDto> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity mapToEntity( UserDto source, String mapId ) {
        return UserMapper.INSTANCE.toEntity( source );
    }

    @Override
    public UserDto mapToDto( UserEntity source, String mapId ) {
        return UserMapper.INSTANCE.toDto( source );
    }

    @Override
    public UserDto create( UserDto user ) {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        return mapToDto( userRepository.save( mapToEntity( user, "" ) ), "" );
    }

    public UserDto findFirstByUsername( String username ) {

        var userEntity = userRepository.findFirstByUsername( username );

        if ( userEntity.isPresent() ) {
            return mapToDto( userEntity.get(), "" );
        } else {
            throw new UsernameNotFoundException( "User details not found for the user : " + username );
        }
    }

    @Override
    public UserDto update( UserDto user ) {
        return null;
    }

    @Override
    public UserDto getById( Integer id ) {
        return null;
    }

    @Override
    public boolean delete( Integer id ) {
        return false;
    }
}
