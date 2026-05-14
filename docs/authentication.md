# Authentication

The SDK currently supports two auth providers:

- `BasicAuthProvider`
- `BearerTokenAuthProvider`

## Basic Auth

```java
ConvoAiClient client = ConvoAiClient.builder()
    .appId(System.getenv("CONVOAI_APP_ID"))
    .authProvider(new BasicAuthProvider(
        System.getenv("CONVOAI_CUSTOMER_ID"),
        System.getenv("CONVOAI_CUSTOMER_SECRET")))
    .build();
```

## Bearer Token

```java
ConvoAiClient client = ConvoAiClient.builder()
    .appId(System.getenv("CONVOAI_APP_ID"))
    .authProvider(new BearerTokenAuthProvider(System.getenv("CONVOAI_RTC_TOKEN")))
    .build();
```

RTC token generation is caller-owned in the current SDK version.
