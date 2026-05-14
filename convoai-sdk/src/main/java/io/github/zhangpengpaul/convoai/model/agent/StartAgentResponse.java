package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StartAgentResponse(
    @JsonProperty("agent_id") String agentId,
    @JsonProperty("create_ts") long createTs,
    @JsonProperty("status") String status
) {
}
