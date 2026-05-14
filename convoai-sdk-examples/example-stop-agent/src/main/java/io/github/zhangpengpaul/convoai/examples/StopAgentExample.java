package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class StopAgentExample {
    private StopAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("stop-agent", support.client().stopAgent(lease.agentId()));
            lease.markStopped();
        }
    }
}
