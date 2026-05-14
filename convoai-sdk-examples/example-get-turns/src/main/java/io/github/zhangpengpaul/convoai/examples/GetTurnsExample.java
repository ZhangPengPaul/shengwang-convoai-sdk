package io.github.zhangpengpaul.convoai.examples;

import io.github.zhangpengpaul.convoai.examples.common.ExampleSupport;
import io.github.zhangpengpaul.convoai.exception.ConvoAiHttpException;
import io.github.zhangpengpaul.convoai.model.agent.GetTurnsRequest;

public final class GetTurnsExample {
    private GetTurnsExample() {
    }

    public static void main(String[] args) {
        ExampleSupport support = ExampleSupport.fromSystem();
        try (var lease = support.useExistingOrCreateAgent()) {
            try {
                support.print("get-turns", support.client().getTurns(lease.agentId(), new GetTurnsRequest(support.environment().requireTurnId())));
            } catch (ConvoAiHttpException exception) {
                support.printHttpException(exception);
                throw exception;
            }
        }
    }
}
