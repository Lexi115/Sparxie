package io.lexi115.sparxie.user.auth.converter;

import io.lexi115.sparxie.user.auth.provider.IdentityProvider;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIdentityProviderConverter implements Converter<String, IdentityProvider> {
    @Override
    public IdentityProvider convert(final @NonNull String source) {
        try {
            return IdentityProvider.valueOf(source.toUpperCase().trim());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
