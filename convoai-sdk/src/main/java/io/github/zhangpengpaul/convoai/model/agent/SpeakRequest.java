package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpeakRequest(
    @JsonProperty("text") String text,
    @JsonProperty("priority") String priority,
    @JsonProperty("interrupt") Boolean interrupt
) {
}
