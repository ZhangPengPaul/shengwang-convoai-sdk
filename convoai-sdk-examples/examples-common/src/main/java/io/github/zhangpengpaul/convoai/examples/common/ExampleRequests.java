package io.github.zhangpengpaul.convoai.examples.common;

import io.github.zhangpengpaul.convoai.model.agent.AgentProperties;
import io.github.zhangpengpaul.convoai.model.agent.AsrConfig;
import io.github.zhangpengpaul.convoai.model.agent.LlmConfig;
import io.github.zhangpengpaul.convoai.model.agent.SpeakRequest;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.ThinkRequest;
import io.github.zhangpengpaul.convoai.model.agent.TtsConfig;
import io.github.zhangpengpaul.convoai.model.agent.UpdateAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.UpdateLlmConfig;
import io.github.zhangpengpaul.convoai.model.common.ChatMessage;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ExampleRequests {
    private ExampleRequests() {
    }

    public static StartAgentRequest defaultStartRequest(ExampleEnvironment environment) {
        environment.requireAgentRuntime();

        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String channel = environment.effectiveChannel() == null || environment.effectiveChannel().isBlank()
            ? "example-channel-" + suffix
            : environment.effectiveChannel();
        String name = "example-agent-" + suffix;
        String remoteRtcUid = environment.effectiveRemoteRtcUid() == null || environment.effectiveRemoteRtcUid().isBlank()
            ? String.valueOf(100000 + Math.abs(UUID.randomUUID().hashCode() % 900000))
            : environment.effectiveRemoteRtcUid();

        Map<String, Object> llmParams = environment.llmParamsJson() == null || environment.llmParamsJson().isBlank()
            ? Map.of("model", "qwen3.6-flash")
            : ExampleJson.readMap(environment.llmParamsJson(), "CONVOAI_LLM_PARAMS_JSON");

        Map<String, Object> ttsParams = ExampleJson.readMap(environment.ttsParamsJson(), "CONVOAI_TTS_PARAMS_JSON");

        return StartAgentRequest.of(
            name,
            new AgentProperties(
                environment.rtcToken(),
                channel,
                environment.effectiveAgentRtcUid(),
                List.of(remoteRtcUid),
                new AsrConfig(environment.asrLanguage()),
                new LlmConfig(
                    environment.llmUrl(),
                    environment.llmApiKey(),
                    List.of(new ChatMessage("system", environment.llmSystemPrompt())),
                    environment.llmGreeting(),
                    environment.llmFailure(),
                    10,
                    llmParams
                ),
                new TtsConfig(environment.ttsVendor(), ttsParams)
            )
        );
    }

    public static UpdateAgentRequest defaultUpdateRequest(ExampleEnvironment environment) {
        Map<String, Object> llmParams = environment.llmParamsJson() == null || environment.llmParamsJson().isBlank()
            ? Map.of("model", "qwen3.6-flash")
            : ExampleJson.readMap(environment.llmParamsJson(), "CONVOAI_LLM_PARAMS_JSON");

        return new UpdateAgentRequest(
            environment.rtcToken(),
            new UpdateLlmConfig(
                List.of(Map.of("role", "system", "content", environment.llmSystemPrompt() + " Please answer in a concise style.")),
                llmParams
            )
        );
    }

    public static SpeakRequest defaultSpeakRequest(ExampleEnvironment environment) {
        return new SpeakRequest(environment.speakText(), "INTERRUPT", true);
    }

    public static ThinkRequest defaultThinkRequest(ExampleEnvironment environment) {
        return ThinkRequest.of(Map.of("input", Map.of("text", environment.thinkText())));
    }
}
