package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.BasicAuthProvider;
import io.github.zhangpengpaul.convoai.model.agent.SpeakRequest;
import io.github.zhangpengpaul.convoai.model.agent.UpdateAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.UpdateLlmConfig;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AgentControlContractTest {

    @Test
    void updateSpeakAndInterruptUseExpectedContracts() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("{\"agent_id\":\"agent-1\",\"create_ts\":1737111452,\"state\":\"RUNNING\"}"));
            server.enqueue(new MockResponse().setBody("{\"agent_id\":\"agent-1\",\"channel\":\"demo\",\"start_ts\":1737111452}"));
            server.enqueue(new MockResponse().setBody("{\"agent_id\":\"agent-1\",\"channel\":\"demo\",\"start_ts\":1737111452}"));
            server.start();

            ConvoAiClient client = ConvoAiClient.builder()
                .appId("app-id")
                .baseUrl(server.url("/cn/api/conversational-ai-agent/v2").toString())
                .authProvider(new BasicAuthProvider("id", "secret"))
                .build();

            client.updateAgent("agent-1", new UpdateAgentRequest("rtc-token", new UpdateLlmConfig(List.of(Map.of("role", "system", "content", "new prompt")), Map.of("model", "demo-v2"))));
            client.speak("agent-1", new SpeakRequest("hello", "INTERRUPT", true));
            client.interrupt("agent-1");

            assertThat(server.takeRequest().getPath()).isEqualTo("/cn/api/conversational-ai-agent/v2/projects/app-id/agents/agent-1/update");
            assertThat(server.takeRequest().getPath()).isEqualTo("/cn/api/conversational-ai-agent/v2/projects/app-id/agents/agent-1/speak");
            assertThat(server.takeRequest().getPath()).isEqualTo("/cn/api/conversational-ai-agent/v2/projects/app-id/agents/agent-1/interrupt");
        }
    }
}
