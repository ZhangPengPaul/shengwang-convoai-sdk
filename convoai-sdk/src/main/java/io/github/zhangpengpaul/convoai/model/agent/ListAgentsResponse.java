package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ListAgentsResponse(
    @JsonProperty("status") String status,
    @JsonProperty("data") Data data,
    @JsonProperty("meta") Meta meta
) {
    public record Data(
        @JsonProperty("count") int count,
        @JsonProperty("list") List<Item> list
    ) {
    }

    public record Item(
        @JsonProperty("start_ts") long startTs,
        @JsonProperty("status") String status,
        @JsonProperty("agent_id") String agentId
    ) {
    }

    public record Meta(
        @JsonProperty("cursor") String cursor,
        @JsonProperty("total") int total
    ) {
    }
}
