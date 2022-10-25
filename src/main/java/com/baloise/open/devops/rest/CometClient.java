package com.baloise.open.devops.rest;

import ch.basler.oidc.DirectGrantToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CometClient {
    private final CometDocumentEndpointConfig cometDocumentEndpointConfig;

    private final WebClient webClient;

    private final DirectGrantToken token;

    public void updateDocument(DocumentDO documentDO, UUID documentId) {
        getWebClient().put()
                .uri(idAppender(documentId))
                .body(Mono.just(documentDO), DocumentDO.class)
                .retrieve()
                .toBodilessEntity().block();
    }

    public void deleteDocument(UUID documentId) {
        getWebClient().delete()
                .uri(idAppender(documentId))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public UUID createDocument(DocumentDO documentDO) {
        return Optional.ofNullable(
                        getWebClient().post()
                                .body(Mono.just(documentDO), DocumentDO.class)
                                .retrieve()
                                .bodyToMono(CreateDocumentResponse.class)
                                .block()
                ).orElseThrow(() -> new RuntimeException("Didn't get a Document Response"))
                .getId();
    }

    private WebClient getWebClient() {
        return webClient
                .mutate().baseUrl(cometDocumentEndpointConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(h -> h.setBearerAuth(token.getToken()))
                .build();
    }

    private Function<UriBuilder, URI> idAppender(UUID id) {
        return builder -> builder.path("{id}").build(id);
    }
}
