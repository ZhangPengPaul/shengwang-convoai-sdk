package io.github.zhangpengpaul.convoai.auth;

import java.net.http.HttpRequest;

public final class BearerTokenAuthProvider implements AuthProvider {
    private final String token;

    public BearerTokenAuthProvider(String token) {
        this.token = token;
    }

    @Override
    public void apply(HttpRequest.Builder builder) {
        builder.header("Authorization", "Bearer " + token);
    }
}
