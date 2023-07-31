package com.kalem.authservertest.user.mapper;


import com.kalem.authservertest.user.model.UserDto;
import com.kalem.authservertest.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );


    UserDto toDto( UserEntity source );


    UserEntity toEntity( UserDto source );
}
