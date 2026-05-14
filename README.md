# Shengwang ConvoAI Java SDK

Java 17 SDK for Shengwang ConvoAI REST APIs, with typed request and response
models, synchronous and asynchronous clients, and runnable per-endpoint
examples.

## Why

Shengwang ConvoAI exposes a REST API surface for creating and controlling
conversation agents. This SDK wraps that surface into a Java-friendly client so
you can:

- create and destroy agents from backend services
- query agent state and list active agents
- send runtime control commands such as `speak`, `interrupt`, and `think`
- retrieve conversation history and turn-level data
- integrate with ConvoAI from plain Java applications without hand-writing HTTP
  requests

## Features

- Java 17
- synchronous API and `CompletableFuture` async API
- `BasicAuthProvider` and `BearerTokenAuthProvider`
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
- per-endpoint example modules

## Installation

```xml
<dependency>
  <groupId>io.github.zhangpengpaul</groupId>
  <artifactId>convoai-sdk</artifactId>
  <version>0.1.0</version>
</dependency>
```

If you are building from source before the first public Central release, adjust
the version to match the current local snapshot.

## Quick Start

```java
ConvoAiClient client = ConvoAiClient.builder()
    .appId(System.getenv("CONVOAI_APP_ID"))
    .authProvider(new BasicAuthProvider(
        System.getenv("CONVOAI_CUSTOMER_ID"),
        System.getenv("CONVOAI_CUSTOMER_SECRET")))
    .build();

GetAgentResponse response = client.getAgent("your-agent-id");
System.out.println(response.status());
```

## Authentication

This SDK deals with two different authentication layers:

### 1. REST API Authentication

Use `BasicAuthProvider` or `BearerTokenAuthProvider` for the HTTP request to the
ConvoAI REST API itself.

```java
ConvoAiClient client = ConvoAiClient.builder()
    .appId(System.getenv("CONVOAI_APP_ID"))
    .authProvider(new BasicAuthProvider(
        System.getenv("CONVOAI_CUSTOMER_ID"),
        System.getenv("CONVOAI_CUSTOMER_SECRET")))
    .build();
```

### 2. RTC Token For Agent Join

When you call `startAgent`, the request body still needs an RTC token for the
agent to join the RTC channel. The SDK does not generate this token for you.

Use `CONVOAI_RTC_TOKEN` as the runtime input for examples, and generate it
according to Shengwang's official Token authentication guide:

- https://doc.shengwang.cn/doc/rtc/android/basic-features/token-authentication

Important:

- the token must match the actual `channel` and `uid` used by the agent
- if the token is generated for a different channel or uid, agent startup will
  fail

## Examples

Each REST operation has its own example module:

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

Shared runtime helpers live in:

- `convoai-sdk-examples/examples-common`

### Example Inputs

Required for all examples:

- `CONVOAI_APP_ID`
- `CONVOAI_CUSTOMER_ID`
- `CONVOAI_CUSTOMER_SECRET`

Required for examples that automatically create a temporary agent:

- `CONVOAI_RTC_TOKEN`
- `CONVOAI_LLM_URL`
- `CONVOAI_LLM_API_KEY`
- `CONVOAI_TTS_VENDOR`
- `CONVOAI_TTS_PARAMS_JSON`

Optional:

- `CONVOAI_AGENT_ID`
- `CONVOAI_CHANNEL`
- `CONVOAI_AGENT_RTC_UID`
- `CONVOAI_REMOTE_RTC_UID`
- `CONVOAI_ASR_LANGUAGE`
- `CONVOAI_LLM_SYSTEM_PROMPT`
- `CONVOAI_LLM_GREETING`
- `CONVOAI_LLM_FAILURE`
- `CONVOAI_LLM_PARAMS_JSON`
- `CONVOAI_SPEAK_TEXT`
- `CONVOAI_THINK_TEXT`
- `CONVOAI_TURN_ID`

`CONVOAI_TTS_PARAMS_JSON` is treated as vendor-specific opaque JSON and passed
through directly.

### Real Validation Status

These examples have already been exercised against the real ConvoAI API:

- `start-agent`
- `get-agent`
- `list-agents`
- `stop-agent`
- `update-agent`
- `speak-agent`
- `interrupt-agent`
- `get-history`
- `think-agent`

`get-turns` still requires a real valid `CONVOAI_TURN_ID`. The example is ready,
but the input must come from an actual conversation turn.

## Project Status

Current focus is a usable Java SDK core plus runnable examples for every REST
endpoint. The next obvious improvement area is release hardening and publishing
workflow.

## Docs

- `docs/api-mapping.md`
- `docs/authentication.md`
- `docs/error-handling.md`
- `docs/maven-central-publishing.md`
