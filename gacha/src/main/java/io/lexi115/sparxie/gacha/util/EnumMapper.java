package io.lexi115.sparxie.gacha.util;

import org.springframework.stereotype.Component;

@Component
public class EnumMapper {
    public String anyEnumToLowerCaseString(final Enum<?> original) {
        return original != null ? original.name().toLowerCase() : null;
    }
}
