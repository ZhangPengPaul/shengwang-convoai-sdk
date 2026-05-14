package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.model.agent.GetAgentResponse;
import io.github.zhangpengpaul.convoai.model.agent.GetHistoryRequest;
import io.github.zhangpengpaul.convoai.model.agent.GetHistoryResponse;
import io.github.zhangpengpaul.convoai.model.agent.GetTurnsRequest;
import io.github.zhangpengpaul.convoai.model.agent.GetTurnsResponse;
import io.github.zhangpengpaul.convoai.model.agent.InterruptResponse;
import io.github.zhangpengpaul.convoai.model.agent.ListAgentsRequest;
import io.github.zhangpengpaul.convoai.model.agent.ListAgentsResponse;
import io.github.zhangpengpaul.convoai.model.agent.SpeakRequest;
import io.github.zhangpengpaul.convoai.model.agent.SpeakResponse;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentResponse;
import io.github.zhangpengpaul.convoai.model.agent.ThinkRequest;
import io.github.zhangpengpaul.convoai.model.agent.ThinkResponse;
import io.github.zhangpengpaul.convoai.model.agent.UpdateAgentRequest;
import io.github.zhangpengpaul.convoai.model.agent.UpdateAgentResponse;
import io.github.zhangpengpaul.convoai.model.common.VoidResponse;

import java.util.concurrent.CompletableFuture;

public final class ConvoAiAsyncClient {
    private final ConvoAiClient client;

    ConvoAiAsyncClient(ConvoAiClient client) {
        this.client = client;
    }

    public ConvoAiClientOptions options() {
        return client.options();
    }

    public CompletableFuture<StartAgentResponse> startAgentAsync(StartAgentRequest request) {
        return CompletableFuture.supplyAsync(() -> client.startAgent(request));
    }

    public CompletableFuture<GetAgentResponse> getAgentAsync(String agentId) {
        return CompletableFuture.supplyAsync(() -> client.getAgent(agentId));
    }

    public CompletableFuture<VoidResponse> stopAgentAsync(String agentId) {
        return CompletableFuture.supplyAsync(() -> client.stopAgent(agentId));
    }

    public CompletableFuture<ListAgentsResponse> listAgentsAsync(ListAgentsRequest request) {
        return CompletableFuture.supplyAsync(() -> client.listAgents(request));
    }

    public CompletableFuture<UpdateAgentResponse> updateAgentAsync(String agentId, UpdateAgentRequest request) {
        return CompletableFuture.supplyAsync(() -> client.updateAgent(agentId, request));
    }

    public CompletableFuture<SpeakResponse> speakAsync(String agentId, SpeakRequest request) {
        return CompletableFuture.supplyAsync(() -> client.speak(agentId, request));
    }

    public CompletableFuture<InterruptResponse> interruptAsync(String agentId) {
        return CompletableFuture.supplyAsync(() -> client.interrupt(agentId));
    }

    public CompletableFuture<GetHistoryResponse> getHistoryAsync(String agentId, GetHistoryRequest request) {
        return CompletableFuture.supplyAsync(() -> client.getHistory(agentId, request));
    }

    public CompletableFuture<ThinkResponse> thinkAsync(String agentId, ThinkRequest request) {
        return CompletableFuture.supplyAsync(() -> client.think(agentId, request));
    }

    public CompletableFuture<GetTurnsResponse> getTurnsAsync(String agentId, GetTurnsRequest request) {
        return CompletableFuture.supplyAsync(() -> client.getTurns(agentId, request));
    }
}
