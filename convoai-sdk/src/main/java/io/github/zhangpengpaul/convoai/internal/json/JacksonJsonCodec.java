package io.github.zhangpengpaul.convoai.internal.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zhangpengpaul.convoai.exception.ConvoAiSerializationException;

public final class JacksonJsonCodec {
    private final ObjectMapper objectMapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public byte[] write(Object body) {
        try {
            return objectMapper.writeValueAsBytes(body);
        } catch (Exception exception) {
            throw new ConvoAiSerializationException("Failed to serialize request body", exception);
        }
    }

    public <T> T read(byte[] body, Class<T> responseType) {
        try {
            return objectMapper.readValue(body, responseType);
        } catch (Exception exception) {
            throw new ConvoAiSerializationException("Failed to deserialize response body", exception);
        }
    }
}
