package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.BearerTokenAuthProvider;
import io.github.zhangpengpaul.convoai.model.agent.GetTurnsRequest;
import io.github.zhangpengpaul.convoai.model.agent.ThinkRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AgentAdvancedContractTest {

    @Test
    void thinkAndGetTurnsExposeRawJsonSafely() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody(Files.readString(Path.of("src/test/resources/contracts/advanced/think-success.json"))));
            server.enqueue(new MockResponse().setBody(Files.readString(Path.of("src/test/resources/contracts/advanced/get-turns-success.json"))));
            server.start();

            ConvoAiClient client = ConvoAiClient.builder()
                .appId("app-id")
                .baseUrl(server.url("/cn/api/conversational-ai-agent/v2").toString())
                .authProvider(new BearerTokenAuthProvider("token"))
                .build();

            var think = client.think("agent-1", ThinkRequest.of(Map.of("input", Map.of("text", "summarize this"))));
            var turns = client.getTurns("agent-1", new GetTurnsRequest("2"));

            assertThat(think.raw().get("agent_id").asText()).isEqualTo("agent-1");
            assertThat(turns.raw().get("turns").get(0).get("turn_id").asText()).isEqualTo("2");
        }
    }
}
