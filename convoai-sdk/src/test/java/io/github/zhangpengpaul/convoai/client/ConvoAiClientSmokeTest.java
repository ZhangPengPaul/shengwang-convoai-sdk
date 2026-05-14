package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.AuthProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvoAiClientSmokeTest {

    @Test
    void builderCapturesCoreOptions() {
        AuthProvider authProvider = builder -> { };

        ConvoAiClient client = ConvoAiClient.builder()
            .appId("test-app-id")
            .authProvider(authProvider)
            .build();

        assertThat(client.options().appId()).isEqualTo("test-app-id");
        assertThat(client.options().authProvider()).isSameAs(authProvider);
        assertThat(client.async()).isNotNull();
    }
}
