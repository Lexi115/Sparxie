package io.lexi115.sparxie.gacha.banner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BannerPullOutcome {
    WIN("win"),
    LOSS("loss"),
    GUARANTEED("guaranteed");

    private final String value;

    BannerPullOutcome(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static BannerPullOutcome fromJson(final String json) {
        return json != null ? BannerPullOutcome.valueOf(json.toUpperCase()) : null;
    }

    @JsonValue
    public String toJson() {
        return this.value;
    }
}
