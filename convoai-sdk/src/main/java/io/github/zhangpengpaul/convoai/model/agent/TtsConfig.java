package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record TtsConfig(
    @JsonProperty("vendor") String vendor,
    @JsonProperty("params") Map<String, Object> params
) {
}
