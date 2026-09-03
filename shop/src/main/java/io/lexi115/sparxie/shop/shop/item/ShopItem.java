package io.lexi115.sparxie.shop.shop.item;

import io.lexi115.sparxie.shop.shop.ShopCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shop_items")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopItem {
    @Id
    private String itemId;

    private ShopCurrency currency;

    private Long cost;
}
