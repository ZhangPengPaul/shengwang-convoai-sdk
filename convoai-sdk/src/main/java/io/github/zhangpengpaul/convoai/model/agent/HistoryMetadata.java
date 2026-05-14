package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HistoryMetadata(
    @JsonProperty("source") String source,
    @JsonProperty("interrupted") Boolean interrupted,
    @JsonProperty("interrupt_timestamp") Long interruptTimestamp,
    @JsonProperty("original") String original,
    @JsonProperty("user") String user
) {
}
