package io.github.zhangpengpaul.convoai.internal.http;

public final class ConvoAiEndpoints {
    public static final Endpoint START_AGENT = new Endpoint("POST", "/projects/{appId}/join");
    public static final Endpoint STOP_AGENT = new Endpoint("POST", "/projects/{appId}/agents/{agentId}/leave");
    public static final Endpoint QUERY_AGENT = new Endpoint("GET", "/projects/{appId}/agents/{agentId}");
    public static final Endpoint LIST_AGENTS = new Endpoint("GET", "/projects/{appId}/agents");
    public static final Endpoint UPDATE_AGENT = new Endpoint("POST", "/projects/{appId}/agents/{agentId}/update");
    public static final Endpoint SPEAK = new Endpoint("POST", "/projects/{appId}/agents/{agentId}/speak");
    public static final Endpoint INTERRUPT = new Endpoint("POST", "/projects/{appId}/agents/{agentId}/interrupt");
    public static final Endpoint HISTORY = new Endpoint("GET", "/projects/{appId}/agents/{agentId}/history");
    public static final Endpoint THINK = new Endpoint("POST", "/projects/{appId}/agents/{agentId}/think");
    public static final Endpoint TURNS = new Endpoint("GET", "/projects/{appId}/agents/{agentId}/turns");

    private ConvoAiEndpoints() {
    }
}
