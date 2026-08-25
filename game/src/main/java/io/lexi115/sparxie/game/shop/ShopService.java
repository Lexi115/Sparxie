package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.inventory.dto.SingleItemRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseJadesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopClient shopClient;

    public void purchaseJades(final PurchaseJadesRequest request) {
        shopClient.purchaseJades(request);
    }

    public void exchangeWithJades(final SingleItemRequest request) {
        shopClient.exchangeWithJades(request);
    }
}
