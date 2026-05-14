package io.github.zhangpengpaul.convoai.model.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AgentProperties(
    @JsonProperty("token") String token,
    @JsonProperty("channel") String channel,
    @JsonProperty("agent_rtc_uid") String agentRtcUid,
    @JsonProperty("remote_rtc_uids") List<String> remoteRtcUids,
    @JsonProperty("asr") AsrConfig asr,
    @JsonProperty("llm") LlmConfig llm,
    @JsonProperty("tts") TtsConfig tts
) {
}
