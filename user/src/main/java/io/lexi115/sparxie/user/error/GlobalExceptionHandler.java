package io.lexi115.sparxie.user.error;

import io.lexi115.sparxie.user.auth.exception.AuthenticationException;
import io.lexi115.sparxie.user.auth.exception.InvalidCredentialsException;
import io.lexi115.sparxie.user.auth.exception.UsernameAlreadyInUseException;
import io.lexi115.sparxie.user.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail playerNotFound(final UserNotFoundException e) {
        return createErrorResponse(
                "User Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_USER_NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ProblemDetail usernameAlreadyInUse(final UsernameAlreadyInUseException e) {
        return createErrorResponse(
                "Username Already Taken", e.getMessage(), HttpStatus.CONFLICT, ErrorCode.ERR_USERNAME_ALREADY_IN_USE);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail invalidCredentials(final InvalidCredentialsException e) {
        return createErrorResponse(
                "Invalid Credentials", e.getMessage(), HttpStatus.UNAUTHORIZED, ErrorCode.ERR_INVALID_CREDENTIALS);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail genericAuthenticationError(final AuthenticationException e) {
        return createErrorResponse(
                "Authentication Error", e.getMessage(), HttpStatus.UNAUTHORIZED, ErrorCode.ERR_AUTHENTICATION_ERROR);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageConversionException.class,
            HttpMessageNotReadableException.class
    })
    public ProblemDetail requestValidationError() {
        return createErrorResponse("Validation Failed", "One or more arguments are invalid.",
                HttpStatus.BAD_REQUEST, ErrorCode.ERR_VALIDATION_FAILED);
    }

    private ProblemDetail createErrorResponse(
            final String title, final String body, final HttpStatusCode statusCode, final ErrorCode errorCode) {
        var errorResponse = ProblemDetail.forStatusAndDetail(statusCode, body);
        errorResponse.setTitle(title);
        if (errorCode != null) {
            errorResponse.setProperty("errorCode", errorCode);
        }
        return errorResponse;
    }
}
