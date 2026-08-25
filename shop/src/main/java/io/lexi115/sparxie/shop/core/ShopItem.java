package io.lexi115.sparxie.shop.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopItem {
    private String itemId;
    private ShopCurrency currency;
    private Long cost;
}
