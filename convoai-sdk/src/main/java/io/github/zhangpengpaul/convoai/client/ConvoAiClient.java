package io.github.zhangpengpaul.convoai.client;

import io.github.zhangpengpaul.convoai.auth.AuthProvider;
import io.github.zhangpengpaul.convoai.exception.ConvoAiValidationException;
import io.github.zhangpengpaul.convoai.internal.http.ConvoAiEndpoints;
import io.github.zhangpengpaul.convoai.internal.http.ConvoAiHttpRequestExecutor;
import io.github.zhangpengpaul.convoai.internal.http.QueryStrings;
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

import java.net.http.HttpClient;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ConvoAiClient {
    private static final String DEFAULT_BASE_URL = "https://api.agora.io/cn/api/conversational-ai-agent/v2";

    private final ConvoAiClientOptions options;
    private final ConvoAiHttpRequestExecutor executor;
    private final ConvoAiAsyncClient asyncClient;

    private ConvoAiClient(ConvoAiClientOptions options) {
        this.options = options;
        this.executor = new ConvoAiHttpRequestExecutor(HttpClient.newHttpClient(), options);
        this.asyncClient = new ConvoAiAsyncClient(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ConvoAiClientOptions options() {
        return options;
    }

    public ConvoAiAsyncClient async() {
        return asyncClient;
    }

    public StartAgentResponse startAgent(StartAgentRequest request) {
        String path = ConvoAiEndpoints.START_AGENT.resolve(Map.of("appId", options.appId()));
        return executor.post(path, request, StartAgentResponse.class);
    }

    public GetAgentResponse getAgent(String agentId) {
        String path = ConvoAiEndpoints.QUERY_AGENT.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.get(path, QueryStrings.empty(), GetAgentResponse.class);
    }

    public VoidResponse stopAgent(String agentId) {
        String path = ConvoAiEndpoints.STOP_AGENT.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.post(path, Map.of(), VoidResponse.class);
    }

    public ListAgentsResponse listAgents(ListAgentsRequest request) {
        String path = ConvoAiEndpoints.LIST_AGENTS.resolve(Map.of("appId", options.appId()));
        return executor.get(path, buildListAgentsQuery(request), ListAgentsResponse.class);
    }

    public UpdateAgentResponse updateAgent(String agentId, UpdateAgentRequest request) {
        String path = ConvoAiEndpoints.UPDATE_AGENT.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.post(path, request, UpdateAgentResponse.class);
    }

    public SpeakResponse speak(String agentId, SpeakRequest request) {
        String path = ConvoAiEndpoints.SPEAK.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.post(path, request, SpeakResponse.class);
    }

    public InterruptResponse interrupt(String agentId) {
        String path = ConvoAiEndpoints.INTERRUPT.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.post(path, Map.of(), InterruptResponse.class);
    }

    public GetHistoryResponse getHistory(String agentId, GetHistoryRequest request) {
        String path = ConvoAiEndpoints.HISTORY.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return executor.get(path, QueryStrings.empty(), GetHistoryResponse.class);
    }

    public ThinkResponse think(String agentId, ThinkRequest request) {
        String path = ConvoAiEndpoints.THINK.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        return new ThinkResponse(executor.post(path, request.body(), com.fasterxml.jackson.databind.JsonNode.class));
    }

    public GetTurnsResponse getTurns(String agentId, GetTurnsRequest request) {
        String path = ConvoAiEndpoints.TURNS.resolve(Map.of("appId", options.appId(), "agentId", agentId));
        String query = request == null || request.turnId() == null ? QueryStrings.empty() : QueryStrings.of(Map.of("turn_id", request.turnId()));
        return new GetTurnsResponse(executor.get(path, query, com.fasterxml.jackson.databind.JsonNode.class));
    }

    public static final class Builder {
        private String appId;
        private String baseUrl = DEFAULT_BASE_URL;
        private AuthProvider authProvider;

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder authProvider(AuthProvider authProvider) {
            this.authProvider = authProvider;
            return this;
        }

        public ConvoAiClient build() {
            if (appId == null || appId.isBlank()) {
                throw new ConvoAiValidationException("appId is required");
            }
            if (authProvider == null) {
                throw new ConvoAiValidationException("authProvider is required");
            }
            return new ConvoAiClient(new ConvoAiClientOptions(appId, baseUrl, authProvider));
        }
    }

    private static String buildListAgentsQuery(ListAgentsRequest request) {
        if (request == null) {
            return QueryStrings.empty();
        }

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("channel", request.channel());
        params.put("limit", request.limit());
        params.put("cursor", request.cursor());
        params.put("from_time", request.fromTime());
        params.put("to_time", request.toTime());
        params.put("state", request.state());
        return QueryStrings.of(params);
    }
}
