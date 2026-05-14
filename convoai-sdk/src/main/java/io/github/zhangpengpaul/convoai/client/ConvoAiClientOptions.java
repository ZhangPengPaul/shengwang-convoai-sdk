package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.AuthProvider;

public record ConvoAiClientOptions(
    String appId,
    String baseUrl,
    AuthProvider authProvider
) {
}
