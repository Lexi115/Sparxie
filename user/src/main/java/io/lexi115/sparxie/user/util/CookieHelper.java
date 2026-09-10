package io.lexi115.sparxie.user.util;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CookieHelper {
    public String extractCookie(final Collection<String> cookieCollection, final String cookieName) {
        if (cookieCollection == null) {
            return null;
        }
        for (var c : cookieCollection) {
            if (c.startsWith(cookieName + "=")) {
                var cookieNameLength = cookieName.length();
                if (c.length() == cookieNameLength + 1) {
                    return "";
                }
                return c.substring(cookieNameLength + 1).split(";")[0];
            }
        }
        return null;
    }
}
