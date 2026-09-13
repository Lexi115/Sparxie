package io.lexi115.sparxie.user.profiles;

import io.lexi115.sparxie.user.profiles.exceptions.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile getById(final UUID userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException(userId));
    }

    public boolean existsById(final UUID userId) {
        return profileRepository.existsById(userId);
    }

    @Transactional
    public void create(final UUID userId, final String username) {
        var profile = Profile.builder()
                .id(userId)
                .username(username)
                .build();
        profileRepository.save(profile);
    }

    @Transactional
    public void deleteById(final UUID userId) {
        var profile = getById(userId);
        profileRepository.delete(profile);
    }
}
