package io.github.zhangpengpaul.convoai.internal.http;

import io.github.zhangpengpaul.convoai.auth.BearerTokenAuthProvider;
import io.github.zhangpengpaul.convoai.client.ConvoAiClientOptions;
import io.github.zhangpengpaul.convoai.exception.ConvoAiHttpException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConvoAiHttpRequestExecutorTest {

    @Test
    void executorMapsNon2xxToHttpException() throws IOException, InterruptedException {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setResponseCode(404).setBody("{\"detail\":\"not found\",\"reason\":\"TaskNotFound\"}"));
            server.start();

            ConvoAiClientOptions options = new ConvoAiClientOptions(
                "app-id",
                server.url("/cn/api/conversational-ai-agent/v2").toString(),
                new BearerTokenAuthProvider("token")
            );

            ConvoAiHttpRequestExecutor executor = new ConvoAiHttpRequestExecutor(HttpClient.newHttpClient(), options);

            assertThatThrownBy(() -> executor.get("/projects/app-id/agents/agent-1", QueryStrings.empty(), byte[].class))
                .isInstanceOf(ConvoAiHttpException.class)
                .hasMessageContaining("not found");

            assertThat(server.takeRequest().getHeader("Authorization")).isEqualTo("Bearer token");
        }
    }
}
