package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.auth.BearerTokenAuthProvider;
import io.github.zhangpengpaul.convoai.client.ConvoAiClient;

public final class BearerTokenAsyncExample {
    private BearerTokenAsyncExample() {
    }

    public static void main(String[] args) {
        ConvoAiClient client = ConvoAiClient.builder()
            .appId(System.getenv("CONVOAI_APP_ID"))
            .authProvider(new BearerTokenAuthProvider(System.getenv("CONVOAI_RTC_TOKEN")))
            .build();

        client.async()
            .getAgentAsync("demo-agent")
            .thenAccept(agent -> System.out.println(agent.status()))
            .exceptionally(error -> {
                System.err.println(error.getMessage());
                return null;
            })
            .join();
    }
}
