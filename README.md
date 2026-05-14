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

- `convoai-sdk-examples/basic-auth-sync`
- `convoai-sdk-examples/bearer-token-async`
