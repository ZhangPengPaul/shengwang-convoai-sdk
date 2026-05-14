package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleRequests;
import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class StartAgentExample {
    private StartAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        support.print("start-agent", support.client().startAgent(ExampleRequests.defaultStartRequest(support.environment())));
    }
}
