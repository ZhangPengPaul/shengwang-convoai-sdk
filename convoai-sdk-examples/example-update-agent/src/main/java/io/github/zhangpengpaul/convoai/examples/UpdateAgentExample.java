package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleRequests;
import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class UpdateAgentExample {
    private UpdateAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("update-agent", support.client().updateAgent(lease.agentId(), ExampleRequests.defaultUpdateRequest(support.environment())));
        }
    }
}
