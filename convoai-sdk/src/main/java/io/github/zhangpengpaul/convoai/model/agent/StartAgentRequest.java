package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StartAgentRequest(
    @JsonProperty("name") String name,
    @JsonProperty("properties") AgentProperties properties
) {
    public static StartAgentRequest of(String name, AgentProperties properties) {
        return new StartAgentRequest(name, properties);
    }
}
