package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record UpdateLlmConfig(
    @JsonProperty("system_messages") List<Map<String, Object>> systemMessages,
    @JsonProperty("params") Map<String, Object> params
) {
}
