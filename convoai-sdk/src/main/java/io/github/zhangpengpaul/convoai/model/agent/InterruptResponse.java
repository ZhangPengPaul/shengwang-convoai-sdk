package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InterruptResponse(
    @JsonProperty("agent_id") String agentId,
    @JsonProperty("channel") String channel,
    @JsonProperty("start_ts") long startTs
) {
}
