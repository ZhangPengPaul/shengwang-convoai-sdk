package io.github.zhangpengpaul.convoai.auth;

import java.net.http.HttpRequest;

@FunctionalInterface
public interface AuthProvider {
    void apply(HttpRequest.Builder builder);
}
