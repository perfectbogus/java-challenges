package dev.perfectbogus.design.patterns.creational.builder.httprequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {

    @Test
    void createHttpRequest_defaults_succeed() {
        HttpRequest request = HttpRequest.builder("http://localhost:8080").build();
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals(0, request.getHeaders().size());
        assertEquals(Optional.empty(), request.getBody());
        assertEquals(30, request.getTimeoutSeconds());
        assertEquals(0, request.getMaxRetries());
    }

    @Test
    void createCustomHttpRequest_customValues_request() {
        final String url = "http://localhost:8080";
        final HttpMethod method = HttpMethod.POST;
        final String headerKey = "Accept";
        final String headerValue = "application/json";
        final String body = "{}";
        final int timeoutSeconds = 10;
        final int maxRetries = 2;
        HttpRequest request = HttpRequest.builder(url)
                .method(method)
                .header(headerKey, headerValue)
                .body(body)
                .timeoutSeconds(timeoutSeconds)
                .maxRetries(maxRetries)
                .build();

        assertEquals(url, request.getUrl());
        assertEquals(method, request.getMethod());
        assertEquals(1, request.getHeaders().size());
        assertEquals(timeoutSeconds, request.getTimeoutSeconds());
        assertEquals(maxRetries, request.getMaxRetries());
    }

    @Test
    void createHttpRequest_modifyHeaders_secureCopy() {
        HttpRequest.Builder requestBuilder = HttpRequest.builder("http://localhost:8080")
                .header("Accept", "application/json");

        HttpRequest first = requestBuilder.build();
        assertEquals(1, first.getHeaders().size());
        assertTrue(first.getHeaders().containsKey("Accept"));
        assertFalse(first.getHeaders().containsKey("Authorization"));

        requestBuilder.header("Authorization", "Bearer abc");
        HttpRequest second = requestBuilder.build();
        assertEquals(2, second.getHeaders().size());
        assertTrue(second.getHeaders().containsKey("Accept"));
        assertTrue(second.getHeaders().containsKey("Authorization"));
    }

    @Test
    void createHttpRequest_withGetAndBody_throws() {
        assertThrows(IllegalStateException.class, () -> HttpRequest.builder("http://localhost:8080")
                .method(HttpMethod.GET)
                .body("{}")
                .build());
    }

    @Test
    void createHttpRequest_zeroTimeout_throws() {
        assertThrows(IllegalStateException.class, () -> HttpRequest.builder("http://localhost:8080")
                .timeoutSeconds(0).build());
    }

    @Test
    void createHttpRequest_negativeRetry_throws() {
        assertThrows(IllegalStateException.class, () -> HttpRequest.builder("http://localhost:8080")
                .maxRetries(-1)
                .build());
    }

    @Test
    void createHttpRequest_nullUrl_throws() {
        assertThrows(NullPointerException.class, () -> HttpRequest.builder(null).build());
    }

    @Test
    void createHttpRequest_noHttpProtocol_throws() {
        assertThrows(IllegalStateException.class, () -> HttpRequest.builder("ftp://files.bank.com").build());
    }
}