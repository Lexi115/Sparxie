package io.lexi115.sparxie.inventory.exception;

import io.lexi115.sparxie.inventory.character.exception.CharacterNotFoundException;
import io.lexi115.sparxie.inventory.inventory.exception.InventoryLockedException;
import io.lexi115.sparxie.inventory.inventory.exception.NotEnoughItemsException;
import io.lexi115.sparxie.inventory.materials.exception.MaterialNotFoundException;
import io.lexi115.sparxie.inventory.player.exception.PlayerNotFoundException;
import io.lexi115.sparxie.inventory.weapon.exception.WeaponNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ProblemDetail playerNotFound(final PlayerNotFoundException e) {
        return createErrorResponse(
                "Player Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_PLAYER_NOT_FOUND);
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ProblemDetail characterNotFound(final CharacterNotFoundException e) {
        return createErrorResponse(
                "Character Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_CHARACTER_NOT_FOUND);
    }

    @ExceptionHandler(WeaponNotFoundException.class)
    public ProblemDetail weaponNotFound(final WeaponNotFoundException e) {
        return createErrorResponse(
                "Weapon Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_WEAPON_NOT_FOUND);
    }

    @ExceptionHandler(MaterialNotFoundException.class)
    public ProblemDetail materialNotFound(final MaterialNotFoundException e) {
        return createErrorResponse(
                "Material Not Found", e.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ERR_MATERIAL_NOT_FOUND);
    }

    @ExceptionHandler(InventoryLockedException.class)
    public ProblemDetail inventoryLocked(final InventoryLockedException e) {
        return createErrorResponse(
                "Inventory Locked", e.getMessage(), HttpStatus.CONFLICT, ErrorCode.ERR_INVENTORY_LOCKED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgument(final IllegalArgumentException e) {
        return createErrorResponse(
                "Illegal argument", e.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.ERR_ILLEGAL_ARGUMENT);
    }

    @ExceptionHandler(NotEnoughItemsException.class)
    public ProblemDetail notEnoughItems(final NotEnoughItemsException e) {
        return createErrorResponse(
                "Not Enough Items",
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_CONTENT,
                ErrorCode.ERR_NOT_ENOUGH_ITEMS,
                Map.of(
                        "itemId", e.getItemId(),
                        "possessedAmount", e.getPossessedAmount(),
                        "requiredAmount", e.getRequiredAmount()
                )
        );
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageConversionException.class,
            HttpMessageNotReadableException.class,
            ConstraintViolationException.class
    })
    public ProblemDetail requestValidationError() {
        return createErrorResponse("Validation Failed", "One or more arguments are invalid.",
                HttpStatus.BAD_REQUEST, ErrorCode.ERR_VALIDATION_FAILED);
    }

    private ProblemDetail createErrorResponse(
            final String title, final String body, final HttpStatusCode statusCode, final ErrorCode errorCode) {
        return createErrorResponse(title, body, statusCode, errorCode, null);
    }

    private ProblemDetail createErrorResponse(
            final String title,
            final String body,
            final HttpStatusCode statusCode,
            final ErrorCode errorCode,
            final Map<String, Object> extraProperties
    ) {
        var errorResponse = ProblemDetail.forStatusAndDetail(statusCode, body);
        errorResponse.setTitle(title);
        if (errorCode != null) {
            errorResponse.setProperty("errorCode", errorCode);
        }
        if (extraProperties != null) {
            extraProperties.forEach(errorResponse::setProperty);
        }
        return errorResponse;
    }
}
