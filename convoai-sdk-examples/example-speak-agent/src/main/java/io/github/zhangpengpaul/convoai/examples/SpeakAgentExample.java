package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleRequests;
import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;

public final class SpeakAgentExample {
    private SpeakAgentExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            support.print("speak-agent", support.client().speak(lease.agentId(), ExampleRequests.defaultSpeakRequest(support.environment())));
        }
    }
}
