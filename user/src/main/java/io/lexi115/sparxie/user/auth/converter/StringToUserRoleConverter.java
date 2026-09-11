package io.lexi115.sparxie.user.auth.converter;

import io.lexi115.sparxie.user.auth.UserRole;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserRoleConverter implements Converter<String, UserRole> {
    @Override
    public UserRole convert(final @NonNull String source) {
        try {
            return UserRole.valueOf(source.toUpperCase().trim());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
