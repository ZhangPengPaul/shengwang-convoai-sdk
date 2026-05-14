package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleRequests;
import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class ThinkAgentExample {
    private ThinkAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("think-agent", support.client().think(lease.agentId(), ExampleRequests.defaultThinkRequest(support.environment())));
        }
    }
}
