package io.lexi115.sparxie.game.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.lexi115.sparxie.game.banner.exception.BannerNotFoundException;
import io.lexi115.sparxie.game.character.exception.CharacterNotFoundException;
import io.lexi115.sparxie.game.inventory.exception.InventoryLockedException;
import io.lexi115.sparxie.game.inventory.exception.NotEnoughItemsException;
import io.lexi115.sparxie.game.player.exception.PlayerNotFoundException;
import io.lexi115.sparxie.game.shop.exception.ShopLockedException;
import io.lexi115.sparxie.game.shop.item.ShopItemNotFoundException;
import io.lexi115.sparxie.game.warp.exception.WarpLockedException;
import io.lexi115.sparxie.game.weapon.exception.WeaponNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try (var bodyIs = response.body().asInputStream()) {
            var problem = objectMapper.readValue(bodyIs, ProblemDetail.class);
            var properties = problem.getProperties();
            if (properties != null) {
                var errorCode = ErrorCode.valueOf(properties.get("errorCode").toString().toUpperCase());
                return switch (errorCode) {
                    case ERR_WARP_LOCKED -> new WarpLockedException(problem.getDetail());
                    case ERR_SHOP_LOCKED -> new ShopLockedException(problem.getDetail());
                    case ERR_INVENTORY_LOCKED -> new InventoryLockedException(problem.getDetail());

                    case ERR_PLAYER_NOT_FOUND -> new PlayerNotFoundException(problem.getDetail());
                    case ERR_BANNER_NOT_FOUND -> new BannerNotFoundException(problem.getDetail());
                    case ERR_CHARACTER_NOT_FOUND -> new CharacterNotFoundException(problem.getDetail());
                    case ERR_WEAPON_NOT_FOUND -> new WeaponNotFoundException(problem.getDetail());
                    case ERR_SHOP_ITEM_NOT_FOUND -> new ShopItemNotFoundException(problem.getDetail());

                    case ERR_NOT_ENOUGH_ITEMS -> parseNotEnoughItemsException(problem);

                    case ERR_VALIDATION_FAILED -> new IllegalArgumentException(problem.getDetail());
                };
            }
        } catch (Exception _) {
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }

    private NotEnoughItemsException parseNotEnoughItemsException(ProblemDetail problem) {
        var properties = problem.getProperties();
        assert properties != null;
        var message = problem.getDetail();
        var itemId = properties.getOrDefault("itemId", "").toString();
        var possessedAmount = Long.valueOf(properties.getOrDefault("possessedAmount", 0L).toString());
        var requiredAmount = Long.valueOf(properties.getOrDefault("requiredAmount", 0L).toString());
        return new NotEnoughItemsException(message, itemId, possessedAmount, requiredAmount);
    }
}
