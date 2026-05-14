package io.github.zhangpengpaul.convoai.internal.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class QueryStrings {
    private QueryStrings() {
    }

    public static String empty() {
        return "";
    }

    public static String of(Map<String, ?> params) {
        return params.entrySet().stream()
            .filter(entry -> Objects.nonNull(entry.getValue()))
            .map(entry -> encode(entry.getKey()) + "=" + encode(String.valueOf(entry.getValue())))
            .collect(Collectors.joining("&"));
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
