package io.github.zhangpengpaul.convoai.model.agent;

public record ListAgentsRequest(
    String channel,
    Integer limit,
    String cursor,
    Long fromTime,
    Long toTime,
    Integer state
) {
}
