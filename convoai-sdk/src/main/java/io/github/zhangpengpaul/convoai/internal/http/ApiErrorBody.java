package io.github.zhangpengpaul.convoai.internal.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiErrorBody(
    @JsonProperty("detail") String detail,
    @JsonProperty("reason") String reason
) {
}
