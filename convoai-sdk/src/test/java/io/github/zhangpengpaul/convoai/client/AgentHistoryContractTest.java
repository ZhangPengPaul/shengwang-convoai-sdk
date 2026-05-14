package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.BearerTokenAuthProvider;
import io.github.zhangpengpaul.convoai.model.agent.GetHistoryRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AgentHistoryContractTest {

    @Test
    void getHistoryPreservesExpandedMemoryFields() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            String body = Files.readString(Path.of("src/test/resources/contracts/history/get-history-success.json"));
            server.enqueue(new MockResponse().setBody(body));
            server.start();

            ConvoAiClient client = ConvoAiClient.builder()
                .appId("app-id")
                .baseUrl(server.url("/cn/api/conversational-ai-agent/v2").toString())
                .authProvider(new BearerTokenAuthProvider("token"))
                .build();

            var response = client.getHistory("agent-1", new GetHistoryRequest());

            assertThat(response.contents()).hasSize(3);
            assertThat(response.contents().get(1).turnId()).isEqualTo(2L);
            assertThat(response.contents().get(1).metadata().source()).isEqualTo("llm");
        }
    }
}
