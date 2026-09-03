package io.lexi115.sparxie.user.user.dto;

import io.lexi115.sparxie.user.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
