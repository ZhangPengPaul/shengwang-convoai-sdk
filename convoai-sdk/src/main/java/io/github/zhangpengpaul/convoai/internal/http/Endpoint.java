package io.github.zhangpengpaul.convoai.internal.http;

import java.util.Map;

public record Endpoint(String method, String template) {
    public String resolve(Map<String, String> pathVariables) {
        String resolved = template;
        for (Map.Entry<String, String> entry : pathVariables.entrySet()) {
            resolved = resolved.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return resolved;
    }
}
