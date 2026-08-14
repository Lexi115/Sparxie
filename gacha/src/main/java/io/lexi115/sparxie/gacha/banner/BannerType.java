package io.lexi115.sparxie.gacha.banner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BannerType {
    CHARACTER_EVENT("character_event"),
    WEAPON_EVENT("weapon_event"),
    STANDARD_EVENT("standard_event");

    private final String value;

    BannerType(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static BannerType fromJson(final String json) {
        return json != null ? BannerType.valueOf(json.toUpperCase()) : null;
    }

    @JsonValue
    public String toJson() {
        return this.value;
    }
}
