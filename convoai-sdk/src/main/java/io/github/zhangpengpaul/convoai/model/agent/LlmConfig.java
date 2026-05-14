package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.zhangpengpaul.convoai.model.common.ChatMessage;

import java.util.List;
import java.util.Map;

public record LlmConfig(
    @JsonProperty("url") String url,
    @JsonProperty("api_key") String apiKey,
    @JsonProperty("system_messages") List<ChatMessage> systemMessages,
    @JsonProperty("greeting_message") String greetingMessage,
    @JsonProperty("failure_message") String failureMessage,
    @JsonProperty("max_history") Integer maxHistory,
    @JsonProperty("params") Map<String, Object> params
) {
}
