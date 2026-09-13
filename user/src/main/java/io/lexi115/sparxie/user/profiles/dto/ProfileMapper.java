package io.lexi115.sparxie.user.profiles.dto;

import io.lexi115.sparxie.user.profiles.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);
}
