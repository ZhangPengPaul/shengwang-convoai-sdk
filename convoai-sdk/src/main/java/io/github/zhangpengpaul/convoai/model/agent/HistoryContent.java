package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HistoryContent(
    @JsonProperty("role") String role,
    @JsonProperty("content") String content,
    @JsonProperty("turn_id") Long turnId,
    @JsonProperty("timestamp") Long timestamp,
    @JsonProperty("metadata") HistoryMetadata metadata
) {
}
