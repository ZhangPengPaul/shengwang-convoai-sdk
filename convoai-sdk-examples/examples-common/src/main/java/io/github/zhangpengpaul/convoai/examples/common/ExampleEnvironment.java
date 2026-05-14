package io.github.zhangpengpaul.convoai.examples.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ExampleEnvironment(
    String appId,
    String customerId,
    String customerSecret,
    String agentId,
    String rtcToken,
    String channel,
    String agentRtcUid,
    String remoteRtcUid,
    String asrLanguage,
    String llmUrl,
    String llmApiKey,
    String llmSystemPrompt,
    String llmGreeting,
    String llmFailure,
    String llmParamsJson,
    String ttsVendor,
    String ttsParamsJson,
    String speakText,
    String thinkText,
    String turnId
) {
    public static ExampleEnvironment fromSystem() {
        return from(System.getenv());
    }

    public static ExampleEnvironment from(Map<String, String> env) {
        return new ExampleEnvironment(
            require(env, "CONVOAI_APP_ID"),
            require(env, "CONVOAI_CUSTOMER_ID"),
            require(env, "CONVOAI_CUSTOMER_SECRET"),
            optional(env, "CONVOAI_AGENT_ID"),
            optional(env, "CONVOAI_RTC_TOKEN"),
            optional(env, "CONVOAI_CHANNEL"),
            optional(env, "CONVOAI_AGENT_RTC_UID"),
            optional(env, "CONVOAI_REMOTE_RTC_UID"),
            optionalOrDefault(env, "CONVOAI_ASR_LANGUAGE", "zh-CN"),
            optional(env, "CONVOAI_LLM_URL"),
            optional(env, "CONVOAI_LLM_API_KEY"),
            optionalOrDefault(env, "CONVOAI_LLM_SYSTEM_PROMPT", "You are a helpful assistant."),
            optionalOrDefault(env, "CONVOAI_LLM_GREETING", "你好，我是示例智能体。"),
            optionalOrDefault(env, "CONVOAI_LLM_FAILURE", "抱歉，我暂时无法回应。"),
            optional(env, "CONVOAI_LLM_PARAMS_JSON"),
            optional(env, "CONVOAI_TTS_VENDOR"),
            optional(env, "CONVOAI_TTS_PARAMS_JSON"),
            optionalOrDefault(env, "CONVOAI_SPEAK_TEXT", "请做一个简短的自我介绍。"),
            optionalOrDefault(env, "CONVOAI_THINK_TEXT", "请总结当前会话。"),
            optional(env, "CONVOAI_TURN_ID")
        );
    }

    public void requireAgentRuntime() {
        List<String> missing = new ArrayList<>();
        collectMissing(missing, "CONVOAI_RTC_TOKEN", rtcToken);
        collectMissing(missing, "CONVOAI_LLM_URL", llmUrl);
        collectMissing(missing, "CONVOAI_LLM_API_KEY", llmApiKey);
        collectMissing(missing, "CONVOAI_TTS_VENDOR", ttsVendor);
        collectMissing(missing, "CONVOAI_TTS_PARAMS_JSON", ttsParamsJson);

        if (!missing.isEmpty()) {
            throw new IllegalArgumentException("Missing required environment variables for agent creation: " + String.join(", ", missing));
        }
    }

    public String effectiveAgentRtcUid() {
        return isBlank(agentRtcUid) ? "0" : agentRtcUid;
    }

    public String effectiveChannel() {
        return channel;
    }

    public String effectiveRemoteRtcUid() {
        return remoteRtcUid;
    }

    public String requireTurnId() {
        if (isBlank(turnId)) {
            throw new IllegalArgumentException("Missing required environment variable: CONVOAI_TURN_ID");
        }
        return turnId;
    }

    private static String require(Map<String, String> env, String key) {
        String value = optional(env, key);
        if (isBlank(value)) {
            throw new IllegalArgumentException("Missing required environment variable: " + key);
        }
        return value;
    }

    private static String optional(Map<String, String> env, String key) {
        return env.get(key);
    }

    private static String optionalOrDefault(Map<String, String> env, String key, String defaultValue) {
        String value = env.get(key);
        return isBlank(value) ? defaultValue : value;
    }

    private static void collectMissing(List<String> missing, String key, String value) {
        if (isBlank(value)) {
            missing.add(key);
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
