package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAgentRequest(
    @JsonProperty("token") String token,
    @JsonProperty("llm") UpdateLlmConfig llm
) {
}
