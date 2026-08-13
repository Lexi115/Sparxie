package io.lexi115.sparxie.game.banner;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BannerCurrency {
    @JsonProperty("limited_ticket")
    LIMITED_TICKET,

    @JsonProperty("standard_ticket")
    STANDARD_TICKET
}
