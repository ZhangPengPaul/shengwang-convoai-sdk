package io.github.zhangpengpaul.convoai.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatMessage(
    @JsonProperty("role") String role,
    @JsonProperty("content") String content
) {
}
