package dev.abidino.event.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface UserMapper {
    User ToUser(UserDto userDto);

    List<User> ToUser(List<UserDto> userDto);

    UserDto ToUserDto(User user);

    List<UserDto> ToUserDto(List<User> user);
}
