package com.zimttech.diabeticscreener.mapper;

import com.zimttech.diabeticscreener.dto.UserDto;
import com.zimttech.diabeticscreener.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User updateUser(@MappingTarget User existingUser, User user);
}
