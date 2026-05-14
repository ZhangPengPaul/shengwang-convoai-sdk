package io.github.zhangpengpaul.convoai.examples.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zhangpengpaul.convoai.auth.BasicAuthProvider;
import io.github.zhangpengpaul.convoai.client.ConvoAiClient;
import io.github.zhangpengpaul.convoai.exception.ConvoAiHttpException;
import io.github.zhangpengpaul.convoai.model.agent.StartAgentResponse;

public final class ExampleSupport {
    private final ExampleEnvironment environment;
    private final ConvoAiClient client;
    private final ObjectMapper objectMapper;

    private ExampleSupport(ExampleEnvironment environment) {
        this.environment = environment;
        this.client = ConvoAiClient.builder()
            .appId(environment.appId())
            .authProvider(new BasicAuthProvider(environment.customerId(), environment.customerSecret()))
            .build();
        this.objectMapper = new ObjectMapper();
    }

    public static ExampleSupport fromSystem() {
        return new ExampleSupport(ExampleEnvironment.fromSystem());
    }

    public ConvoAiClient client() {
        return client;
    }

    public ExampleEnvironment environment() {
        return environment;
    }

    public ExampleAgentLease useExistingOrCreateAgent() {
        if (environment.agentId() != null && !environment.agentId().isBlank()) {
            return new ExampleAgentLease(client, environment.agentId(), false);
        }

        StartAgentResponse response = client.startAgent(ExampleRequests.defaultStartRequest(environment));
        print("created-agent", response);
        return new ExampleAgentLease(client, response.agentId(), true);
    }

    public void print(String label, Object value) {
        try {
            System.out.println("=== " + label + " ===");
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
        } catch (Exception exception) {
            throw new RuntimeException("Failed to print " + label, exception);
        }
    }

    public void printHttpException(ConvoAiHttpException exception) {
        System.err.println("=== http-error ===");
        System.err.println("statusCode=" + exception.statusCode());
        System.err.println("errorCode=" + exception.errorCode());
        System.err.println("message=" + exception.getMessage());
        System.err.println("responseBody=" + exception.responseBody());
    }
}
