package io.lexi115.sparxie.shop.error;

import io.lexi115.sparxie.shop.item.exception.ShopItemNotFoundException;
import io.lexi115.sparxie.shop.shop.exception.ShopLockedException;
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
