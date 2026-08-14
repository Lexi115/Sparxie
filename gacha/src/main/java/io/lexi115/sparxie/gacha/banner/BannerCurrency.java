package io.lexi115.sparxie.gacha.banner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BannerCurrency {

    LIMITED_TICKET("limited_ticket"),

    STANDARD_TICKET("standard_ticket");

    private final String value;

    BannerCurrency(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static BannerCurrency fromJson(final String json) {
        return json != null ? BannerCurrency.valueOf(json.toUpperCase()) : null;
    }

    @JsonValue
    public String toJson() {
        return this.value;
    }
}
