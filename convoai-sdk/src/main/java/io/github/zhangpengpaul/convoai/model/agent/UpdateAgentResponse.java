package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAgentResponse(
    @JsonProperty("agent_id") String agentId,
    @JsonProperty("create_ts") long createTs,
    @JsonProperty("state") String state
) {
}
