package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetAgentResponse(
    @JsonProperty("message") String message,
    @JsonProperty("start_ts") long startTs,
    @JsonProperty("stop_ts") long stopTs,
    @JsonProperty("agent_id") String agentId,
    @JsonProperty("status") String status
) {
}
