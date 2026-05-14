package io.github.zhangpengpaul.convoai.auth;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class BasicAuthProvider implements AuthProvider {
    private final String username;
    private final String password;

    public BasicAuthProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void apply(HttpRequest.Builder builder) {
        String credentials = Base64.getEncoder()
            .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        builder.header("Authorization", "Basic " + credentials);
    }
}
