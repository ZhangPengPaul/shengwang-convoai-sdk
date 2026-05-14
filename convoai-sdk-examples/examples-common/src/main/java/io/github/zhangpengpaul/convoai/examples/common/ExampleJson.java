package io.github.zhangpengpaul.convoai.examples.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

final class ExampleJson {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ExampleJson() {
    }

    static Map<String, Object> readMap(String raw, String variableName) {
        try {
            return OBJECT_MAPPER.readValue(raw, new TypeReference<>() { });
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid JSON in " + variableName, exception);
        }
    }
}
