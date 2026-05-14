package io.github.zhangpengpaul.convoai.examples.common;

import io.github.zhangpengpaul.convoai.client.ConvoAiClient;
public final class ExampleAgentLease implements AutoCloseable {
    private final ConvoAiClient client;
    private final String agentId;
    private final boolean temporary;
    private boolean stopped;

    ExampleAgentLease(ConvoAiClient client, String agentId, boolean temporary) {
        this.client = client;
        this.agentId = agentId;
        this.temporary = temporary;
    }

    public String agentId() {
        return agentId;
    }

    public boolean temporary() {
        return temporary;
    }

    public void stopNow() {
        if (!stopped) {
            client.stopAgent(agentId);
            stopped = true;
        }
    }

    public void markStopped() {
        stopped = true;
    }

    @Override
    public void close() {
        if (temporary && !stopped) {
            stopNow();
        }
    }
}
