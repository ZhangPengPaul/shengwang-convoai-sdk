package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GetHistoryResponse(
    @JsonProperty("start_ts") long startTs,
    @JsonProperty("agent_id") String agentId,
    @JsonProperty("status") String status,
    @JsonProperty("contents") List<HistoryContent> contents
) {
}
