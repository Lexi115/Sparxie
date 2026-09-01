package io.lexi115.sparxie.game.error;

import io.lexi115.sparxie.game.banner.exception.BannerNotFoundException;
import io.lexi115.sparxie.game.character.exception.CharacterNotFoundException;
import io.lexi115.sparxie.game.inventory.exception.InventoryLockedException;
import io.lexi115.sparxie.game.inventory.exception.NotEnoughItemsException;
import io.lexi115.sparxie.game.material.exception.MaterialNotFoundException;
import io.lexi115.sparxie.game.player.exception.PlayerNotFoundException;
import io.lexi115.sparxie.game.shop.exception.ShopLockedException;
import io.lexi115.sparxie.game.shop.item.ShopItemNotFoundException;
import io.lexi115.sparxie.game.warp.exception.WarpLockedException;
import io.lexi115.sparxie.game.weapon.exception.WeaponNotFoundException;
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
