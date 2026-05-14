package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class InterruptAgentExample {
    private InterruptAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("interrupt-agent", support.client().interrupt(lease.agentId()));
        }
    }
}
