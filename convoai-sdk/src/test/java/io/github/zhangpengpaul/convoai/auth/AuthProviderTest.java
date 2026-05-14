package io.github.zhangpengpaul.convoai.auth;

import io.github.zhangpengpaul.convoai.client.ConvoAiClient;
import io.github.zhangpengpaul.convoai.exception.ConvoAiValidationException;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthProviderTest {

    @Test
    void basicAuthProviderAddsAuthorizationHeader() {
        HttpRequest.Builder builder = HttpRequest.newBuilder();

        new BasicAuthProvider("customer-id", "customer-secret").apply(builder);

        HttpRequest request = builder.uri(URI.create("https://example.com")).build();
        assertThat(request.headers().firstValue("Authorization"))
            .hasValueSatisfying(value -> assertThat(value).startsWith("Basic "));
    }

    @Test
    void builderRejectsMissingRequiredFields() {
        assertThatThrownBy(() -> ConvoAiClient.builder().build())
            .isInstanceOf(ConvoAiValidationException.class)
            .hasMessageContaining("appId");
    }
}
