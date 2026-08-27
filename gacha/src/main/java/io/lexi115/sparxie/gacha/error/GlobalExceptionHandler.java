package io.lexi115.sparxie.gacha.error;

import io.lexi115.sparxie.gacha.banner.BannerNotFoundException;
import io.lexi115.sparxie.gacha.player.PlayerNotFoundException;
import io.lexi115.sparxie.gacha.warp.WarpLockedException;
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

    @ExceptionHandler(PlayerNotFoundException.class)
    public ProblemDetail playerNotFound(final PlayerNotFoundException e) {
        return createErrorResponse(
                "Player Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_PLAYER_NOT_FOUND);
    }

    @ExceptionHandler(BannerNotFoundException.class)
    public ProblemDetail bannerNotFound(final BannerNotFoundException e) {
        return createErrorResponse(
                "Banner Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_BANNER_NOT_FOUND);
    }

    @ExceptionHandler(WarpLockedException.class)
    public ProblemDetail warpLocked(final WarpLockedException e) {
        return createErrorResponse("Warp Locked", e.getMessage(), HttpStatus.CONFLICT, ErrorCode.ERR_WARP_LOCKED);
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
