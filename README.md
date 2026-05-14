# Shengwang ConvoAI Java SDK

Community-maintained Java 17 SDK for Shengwang ConvoAI REST APIs.

## Status

Current scope:

- Java 17
- synchronous client
- `CompletableFuture` async client
- basic auth and bearer token auth
- typed wrappers for:
  - start agent
  - stop agent
  - update agent
  - query agent status
  - get agent list
  - agent speak
  - agent interrupt
  - agent think
  - get history
  - get turns

## Install

```xml
<dependency>
  <groupId>io.github.zhangpengpaul</groupId>
  <artifactId>convoai-sdk</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

## Quick Start

```java
ConvoAiClient client = ConvoAiClient.builder()
    .appId(System.getenv("CONVOAI_APP_ID"))
    .authProvider(new BearerTokenAuthProvider(System.getenv("CONVOAI_RTC_TOKEN")))
    .build();

StartAgentResponse response = client.startAgent(
    StartAgentRequest.of(
        "demo-agent",
        new AgentProperties(
            System.getenv("CONVOAI_RTC_TOKEN"),
            "demo-channel",
            "0",
            List.of("123"),
            new AsrConfig("zh-CN"),
            new LlmConfig(
                "https://llm.example.com",
                "llm-key",
                List.of(new ChatMessage("system", "You are helpful")),
                "Hello",
                "Sorry, something went wrong.",
                10,
                Map.of("model", "demo")
            ),
            new TtsConfig("minimax", Map.of("model", "speech-01-turbo"))
        )
    )
);

System.out.println(response.agentId());
```

## Docs

- `docs/api-mapping.md`
- `docs/authentication.md`
- `docs/error-handling.md`

## Examples

- `convoai-sdk-examples/examples-common`
- `convoai-sdk-examples/example-start-agent`
- `convoai-sdk-examples/example-stop-agent`
- `convoai-sdk-examples/example-update-agent`
- `convoai-sdk-examples/example-get-agent`
- `convoai-sdk-examples/example-list-agents`
- `convoai-sdk-examples/example-speak-agent`
- `convoai-sdk-examples/example-interrupt-agent`
- `convoai-sdk-examples/example-think-agent`
- `convoai-sdk-examples/example-get-history`
- `convoai-sdk-examples/example-get-turns`

### Example Environment Variables

Required for all examples:

- `CONVOAI_APP_ID`
- `CONVOAI_CUSTOMER_ID`
- `CONVOAI_CUSTOMER_SECRET`

Required for examples that create a temporary agent automatically:

- `CONVOAI_RTC_TOKEN`
- `CONVOAI_LLM_URL`
- `CONVOAI_LLM_API_KEY`
- `CONVOAI_TTS_VENDOR`
- `CONVOAI_TTS_PARAMS_JSON`

Optional:

- `CONVOAI_AGENT_ID`
- `CONVOAI_AGENT_RTC_UID`
- `CONVOAI_ASR_LANGUAGE`
- `CONVOAI_LLM_SYSTEM_PROMPT`
- `CONVOAI_LLM_GREETING`
- `CONVOAI_LLM_FAILURE`
- `CONVOAI_LLM_PARAMS_JSON`
- `CONVOAI_SPEAK_TEXT`
- `CONVOAI_THINK_TEXT`
- `CONVOAI_TURN_ID`

The examples treat `CONVOAI_TTS_PARAMS_JSON` as vendor-specific opaque JSON and
pass it through directly.
