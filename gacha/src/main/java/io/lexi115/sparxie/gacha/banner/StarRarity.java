package io.lexi115.sparxie.gacha.banner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StarRarity {
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final Integer value;

    StarRarity(final Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static StarRarity fromJson(final String json) {
        return json == null ? null : switch (json) {
            case "3" -> THREE;
            case "4" -> FOUR;
            case "5" -> FIVE;
            default -> null;
        };
    }

    @JsonValue
    public Integer toJson() {
        return this.value;
    }
}
