package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

public final class ThinkRequest {
    private final Map<String, Object> body;

    private ThinkRequest(Map<String, Object> body) {
        this.body = body;
    }

    public static ThinkRequest of(Map<String, Object> body) {
        return new ThinkRequest(body);
    }

    @JsonAnyGetter
    public Map<String, Object> body() {
        return body;
    }
}
