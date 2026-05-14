package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.auth.BasicAuthProvider;
import io.github.zhangpengpaul.convoai.client.ConvoAiClient;

public final class BasicAuthSyncExample {
    private BasicAuthSyncExample() {
    }

    public static void main(String[] args) {
        ConvoAiClient client = ConvoAiClient.builder()
            .appId(System.getenv("CONVOAI_APP_ID"))
            .authProvider(new BasicAuthProvider(
                System.getenv("CONVOAI_CUSTOMER_ID"),
                System.getenv("CONVOAI_CUSTOMER_SECRET")))
            .build();

        System.out.println("ConvoAI client ready with appId=" + client.options().appId());
    }
}
