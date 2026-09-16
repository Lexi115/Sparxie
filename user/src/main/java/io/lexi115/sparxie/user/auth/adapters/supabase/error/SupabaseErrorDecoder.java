package io.lexi115.sparxie.user.auth.adapters.supabase.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.lexi115.sparxie.user.auth.exceptions.*;
import io.lexi115.sparxie.user.profiles.exceptions.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class SupabaseErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try (var bodyIs = response.body().asInputStream()) {
            var errorResponse = objectMapper.readValue(bodyIs, SupabaseErrorResponse.class);
            var errorMessage = getErrorMessage(errorResponse);
            return switch (getErrorCode(errorResponse)) {
                case "invalid_grant" -> parseInvalidGrantError(errorMessage);
                case "user_already_exists" -> new UserAlreadyExistsException();
                case "user_not_found" -> new ProfileNotFoundException();
                case "weak_password", "same_password" -> new InvalidCredentialsException(errorMessage);
                case "validation_failed" -> new InvalidRequestException();
                default -> new AuthenticationException(errorMessage);
            };
        } catch (Exception _) {
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getErrorCode(SupabaseErrorResponse errorResponse) {
        var errorCode = errorResponse.errorCode();
        return errorCode != null ? errorCode : errorResponse.error();
    }

    private String getErrorMessage(SupabaseErrorResponse errorResponse) {
        var errorDescription = errorResponse.errorDescription();
        return errorDescription != null ? errorDescription : errorResponse.message();
    }

    private Exception parseInvalidGrantError(final String errorMessage) {
        if (errorMessage.startsWith("Invalid login credentials")) {
            return new InvalidCredentialsException();
        } else if (errorMessage.startsWith("Invalid Refresh Token")) {
            return new InvalidRefreshTokenException();
        } else {
            return new AuthenticationException(errorMessage);
        }
    }
}
