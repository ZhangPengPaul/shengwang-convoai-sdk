package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.BearerTokenAuthProvider;
import io.github.zhangpengpaul.convoai.model.agent.AgentProperties;
import io.github.zhangpengpaul.convoai.model.agent.AsrConfig;
import io.github.zhangpengpaul.convoai.model.agent.GetAgentResponse;
import io.github.zhangpengpaul.convoai.model.agent.LlmConfig;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentResponse;
import io.github.zhangpengpaul.convoai.model.agent.TtsConfig;
import io.github.zhangpengpaul.convoai.model.common.ChatMessage;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AgentLifecycleContractTest {

    @Test
    void startAndQueryUseExpectedPaths() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("{\"agent_id\":\"agent-1\",\"create_ts\":1737111452,\"status\":\"RUNNING\"}"));
            server.enqueue(new MockResponse().setBody("{\"message\":\"OK\",\"start_ts\":1737111452,\"stop_ts\":0,\"agent_id\":\"agent-1\",\"status\":\"RUNNING\"}"));
            server.start();

            ConvoAiClient client = ConvoAiClient.builder()
                .appId("app-id")
                .baseUrl(server.url("/cn/api/conversational-ai-agent/v2").toString())
                .authProvider(new BearerTokenAuthProvider("token"))
                .build();

            StartAgentRequest request = StartAgentRequest.of(
                "unique-name",
                new AgentProperties(
                    "rtc-token",
                    "test-channel",
                    "0",
                    List.of("123"),
                    new AsrConfig("zh-CN"),
                    new LlmConfig(
                        "https://llm.example.com",
                        "llm-key",
                        List.of(new ChatMessage("system", "You are helpful")),
                        "hi",
                        "failed",
                        10,
                        Map.of("model", "demo")
                    ),
                    new TtsConfig("minimax", Map.of("model", "speech-01-turbo"))
                )
            );

            StartAgentResponse start = client.startAgent(request);
            GetAgentResponse query = client.getAgent("agent-1");

            assertThat(start.agentId()).isEqualTo("agent-1");
            assertThat(query.status()).isEqualTo("RUNNING");
            assertThat(server.takeRequest().getPath()).isEqualTo("/cn/api/conversational-ai-agent/v2/projects/app-id/join");
            assertThat(server.takeRequest().getPath()).isEqualTo("/cn/api/conversational-ai-agent/v2/projects/app-id/agents/agent-1");
        }
    }
}
