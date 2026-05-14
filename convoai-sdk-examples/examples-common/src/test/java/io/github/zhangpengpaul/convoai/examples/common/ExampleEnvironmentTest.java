package io.github.zhangpengpaul.convoai.examples.common;

import io.github.zhangpengpaul.convoai.model.agent.StartAgentRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExampleEnvironmentTest {

    @Test
    void requiresBasicAuthEnvironmentVariables() {
        assertThatThrownBy(() -> ExampleEnvironment.from(Map.of()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CONVOAI_APP_ID");
    }

    @Test
    void buildsDefaultStartRequestWithGeneratedRuntimeValues() {
        ExampleEnvironment environment = ExampleEnvironment.from(Map.of(
            "CONVOAI_APP_ID", "app-id",
            "CONVOAI_CUSTOMER_ID", "customer-id",
            "CONVOAI_CUSTOMER_SECRET", "customer-secret",
            "CONVOAI_RTC_TOKEN", "rtc-token",
            "CONVOAI_LLM_URL", "https://dashscope.aliyuncs.com/compatible-mode/",
            "CONVOAI_LLM_API_KEY", "llm-key",
            "CONVOAI_TTS_VENDOR", "bytedance_duplex",
            "CONVOAI_TTS_PARAMS_JSON", "{\"token\":\"tts-token\",\"speaker\":\"demo-speaker\"}"
        ));

        StartAgentRequest request = ExampleRequests.defaultStartRequest(environment);

        assertThat(request.name()).startsWith("example-agent-");
        assertThat(request.properties().channel()).startsWith("example-channel-");
        assertThat(request.properties().remoteRtcUids()).hasSize(1);
        assertThat(request.properties().llm().params()).containsEntry("model", "qwen3.6-flash");
        assertThat(request.properties().tts().vendor()).isEqualTo("bytedance_duplex");
    }

    @Test
    void usesFixedChannelAndRtcUidsWhenProvided() {
        ExampleEnvironment environment = ExampleEnvironment.from(Map.ofEntries(
            Map.entry("CONVOAI_APP_ID", "app-id"),
            Map.entry("CONVOAI_CUSTOMER_ID", "customer-id"),
            Map.entry("CONVOAI_CUSTOMER_SECRET", "customer-secret"),
            Map.entry("CONVOAI_RTC_TOKEN", "rtc-token"),
            Map.entry("CONVOAI_CHANNEL", "convoai-example-e2e-001"),
            Map.entry("CONVOAI_AGENT_RTC_UID", "10001"),
            Map.entry("CONVOAI_REMOTE_RTC_UID", "10002"),
            Map.entry("CONVOAI_LLM_URL", "https://dashscope.aliyuncs.com/compatible-mode/"),
            Map.entry("CONVOAI_LLM_API_KEY", "llm-key"),
            Map.entry("CONVOAI_TTS_VENDOR", "bytedance_duplex"),
            Map.entry("CONVOAI_TTS_PARAMS_JSON", "{\"token\":\"tts-token\",\"speaker\":\"demo-speaker\"}")
        ));

        StartAgentRequest request = ExampleRequests.defaultStartRequest(environment);

        assertThat(request.properties().channel()).isEqualTo("convoai-example-e2e-001");
        assertThat(request.properties().agentRtcUid()).isEqualTo("10001");
        assertThat(request.properties().remoteRtcUids()).containsExactly("10002");
    }
}
