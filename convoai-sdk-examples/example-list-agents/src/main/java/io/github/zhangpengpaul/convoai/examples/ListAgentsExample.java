package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;
import io.github.zhangpengpaul.convoai.model.agent.ListAgentsRequest;

public final class ListAgentsExample {
    private ListAgentsExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        support.print("list-agents", support.client().listAgents(new ListAgentsRequest(null, 20, null, null, null, null)));
    }
}
