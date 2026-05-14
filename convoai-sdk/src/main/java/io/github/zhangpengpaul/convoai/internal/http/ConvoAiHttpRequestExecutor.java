package io.github.zhangpengpaul.convoai.internal.http;

import io.github.zhangpengpaul.convoai.client.ConvoAiClientOptions;
import io.github.zhangpengpaul.convoai.exception.ConvoAiException;
import io.github.zhangpengpaul.convoai.exception.ConvoAiHttpException;
import io.github.zhangpengpaul.convoai.internal.json.JacksonJsonCodec;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public final class ConvoAiHttpRequestExecutor {
    private final HttpClient httpClient;
    private final ConvoAiClientOptions options;
    private final JacksonJsonCodec codec;

    public ConvoAiHttpRequestExecutor(HttpClient httpClient, ConvoAiClientOptions options) {
        this(httpClient, options, new JacksonJsonCodec());
    }

    public ConvoAiHttpRequestExecutor(HttpClient httpClient, ConvoAiClientOptions options, JacksonJsonCodec codec) {
        this.httpClient = httpClient;
        this.options = options;
        this.codec = codec;
    }

    public <T> T get(String path, String queryString, Class<T> responseType) {
        return send("GET", path, queryString, null, responseType);
    }

    public <T> T post(String path, Object body, Class<T> responseType) {
        return send("POST", path, QueryStrings.empty(), body, responseType);
    }

    public <T> T send(String method, String path, String queryString, Object body, Class<T> responseType) {
        try {
            HttpRequest request = newRequest(method, path, queryString, body);
            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() / 100 != 2) {
                String rawBody = new String(response.body(), StandardCharsets.UTF_8);
                ApiErrorBody errorBody = codec.read(response.body(), ApiErrorBody.class);
                throw new ConvoAiHttpException(response.statusCode(), errorBody.reason(), errorBody.detail(), rawBody);
            }

            if (responseType == byte[].class) {
                return responseType.cast(response.body());
            }
            return codec.read(response.body(), responseType);
        } catch (ConvoAiException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ConvoAiException("HTTP request failed", exception);
        }
    }

    private HttpRequest newRequest(String method, String path, String queryString, Object body) {
        String suffix = queryString == null || queryString.isBlank() ? "" : "?" + queryString;
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(options.baseUrl() + path + suffix))
            .header("Content-Type", "application/json");
        options.authProvider().apply(builder);

        if ("POST".equals(method)) {
            byte[] payload = body == null ? "{}".getBytes(StandardCharsets.UTF_8) : codec.write(body);
            builder.POST(HttpRequest.BodyPublishers.ofByteArray(payload));
        } else {
            builder.GET();
        }

        return builder.build();
    }
}
