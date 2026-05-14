package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AsrConfig(
    @JsonProperty("language") String language
) {
}
