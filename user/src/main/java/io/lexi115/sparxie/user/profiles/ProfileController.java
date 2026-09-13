package io.lexi115.sparxie.user.profiles;

import io.lexi115.sparxie.user.profiles.dto.ProfileDto;
import io.lexi115.sparxie.user.profiles.dto.ProfileMapper;
import io.lexi115.sparxie.user.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;
    private final JwtHelper jwtHelper;

    @GetMapping("/me")
    public ProfileDto getAuthenticatedUserProfile(@RequestHeader("Authorization") final String authToken) {
        var userId = jwtHelper.extractSubject(authToken, UUID.class);
        var profile = profileService.getById(userId);
        return profileMapper.toDto(profile);
    }

    @GetMapping("/admin/users/{userId}")
    public ProfileDto getById(@PathVariable final UUID userId) {
        var user = profileService.getById(userId);
        return profileMapper.toDto(user);
    }
}
