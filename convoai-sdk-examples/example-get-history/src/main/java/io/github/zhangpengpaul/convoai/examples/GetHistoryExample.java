package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;
import io.github.zhangpengpaul.convoai.model.agent.GetHistoryRequest;

public final class GetHistoryExample {
    private GetHistoryExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("get-history", support.client().getHistory(lease.agentId(), new GetHistoryRequest()));
        }
    }
}
