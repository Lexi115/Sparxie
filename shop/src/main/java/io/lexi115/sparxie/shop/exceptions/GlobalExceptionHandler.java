package io.lexi115.sparxie.shop.exceptions;

import io.lexi115.sparxie.shop.shop.exceptions.ShopLockedException;
import io.lexi115.sparxie.shop.shop.items.exceptions.ShopItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShopItemNotFoundException.class)
    public ProblemDetail shopItemNotFound(final ShopItemNotFoundException e) {
        return createErrorResponse(
                "Shop Item Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_SHOP_ITEM_NOT_FOUND);
    }

    @ExceptionHandler(ShopLockedException.class)
    public ProblemDetail shopLocked(final ShopLockedException e) {
        return createErrorResponse("Shop Locked", e.getMessage(), HttpStatus.CONFLICT, ErrorCode.ERR_SHOP_LOCKED);
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail httpMethodNotSupported(final HttpRequestMethodNotSupportedException e) {
        return createErrorResponse("Operation not supported", "Method '" + e.getMethod() + "' not supported!",
                HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.ERR_METHOD_NOT_SUPPORTED);
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
