package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class GetAgentExample {
    private GetAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("get-agent", support.client().getAgent(lease.agentId()));
        }
    }
}
