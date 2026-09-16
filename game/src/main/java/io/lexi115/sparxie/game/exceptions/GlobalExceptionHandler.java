package io.lexi115.sparxie.game.exceptions;

import io.lexi115.sparxie.game.banners.exception.BannerNotFoundException;
import io.lexi115.sparxie.game.characters.exception.CharacterNotFoundException;
import io.lexi115.sparxie.game.inventories.exception.InventoryLockedException;
import io.lexi115.sparxie.game.inventories.exception.NotEnoughItemsException;
import io.lexi115.sparxie.game.materials.exception.MaterialNotFoundException;
import io.lexi115.sparxie.game.players.exceptions.PlayerNotFoundException;
import io.lexi115.sparxie.game.shop.exceptions.ShopLockedException;
import io.lexi115.sparxie.game.shop.items.ShopItemNotFoundException;
import io.lexi115.sparxie.game.warp.exceptions.WarpLockedException;
import io.lexi115.sparxie.game.weapons.exceptions.WeaponNotFoundException;
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

    @ExceptionHandler(PlayerNotFoundException.class)
    public ProblemDetail playerNotFound(final PlayerNotFoundException e) {
        return createErrorResponse("Player Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_PLAYER_NOT_FOUND);
    }

    @ExceptionHandler(BannerNotFoundException.class)
    public ProblemDetail bannerNotFound(final BannerNotFoundException e) {
        return createErrorResponse("Banner Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_BANNER_NOT_FOUND);
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ProblemDetail characterNotFound(final CharacterNotFoundException e) {
        return createErrorResponse("Character Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_CHARACTER_NOT_FOUND);
    }

    @ExceptionHandler(WeaponNotFoundException.class)
    public ProblemDetail weaponNotFound(final WeaponNotFoundException e) {
        return createErrorResponse("Weapon Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_WEAPON_NOT_FOUND);
    }

    @ExceptionHandler(MaterialNotFoundException.class)
    public ProblemDetail materialNotFound(final MaterialNotFoundException e) {
        return createErrorResponse("Material Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_WEAPON_NOT_FOUND);
    }

    @ExceptionHandler(ShopItemNotFoundException.class)
    public ProblemDetail shopItemNotFound(final ShopItemNotFoundException e) {
        return createErrorResponse("Shop Item Not Found", e.getMessage(),
                HttpStatus.NOT_FOUND, ErrorCode.ERR_SHOP_ITEM_NOT_FOUND);
    }

    @ExceptionHandler(WarpLockedException.class)
    public ProblemDetail warpLocked(final WarpLockedException e) {
        return createErrorResponse("Warp Locked", e.getMessage(),
                HttpStatus.CONFLICT, ErrorCode.ERR_WARP_LOCKED);
    }

    @ExceptionHandler(InventoryLockedException.class)
    public ProblemDetail inventoryLocked(final InventoryLockedException e) {
        return createErrorResponse("Inventory Locked", e.getMessage(),
                HttpStatus.CONFLICT, ErrorCode.ERR_INVENTORY_LOCKED);
    }

    @ExceptionHandler(ShopLockedException.class)
    public ProblemDetail shopLocked(final ShopLockedException e) {
        return createErrorResponse("Shop Locked", e.getMessage(),
                HttpStatus.CONFLICT, ErrorCode.ERR_SHOP_LOCKED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgument(final IllegalArgumentException e) {
        return createErrorResponse("Illegal Argument", e.getMessage(),
                HttpStatus.BAD_REQUEST, ErrorCode.ERR_ILLEGAL_ARGUMENT);
    }

    @ExceptionHandler(NotEnoughItemsException.class)
    public ProblemDetail notEnoughItems(final NotEnoughItemsException e) {
        var errorResponse = createErrorResponse("Not Enough Items", e.getMessage(),
                HttpStatus.UNPROCESSABLE_CONTENT, ErrorCode.ERR_NOT_ENOUGH_ITEMS);
        errorResponse.setProperty("itemId", e.getItemId());
        errorResponse.setProperty("possessedAmount", e.getPossessedAmount());
        errorResponse.setProperty("requiredAmount", e.getRequiredAmount());

        return errorResponse;
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
            errorResponse.setProperty("errorCode", errorCode.name());
        }
        return errorResponse;
    }
}
