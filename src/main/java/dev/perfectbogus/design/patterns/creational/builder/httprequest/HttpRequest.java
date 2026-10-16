package dev.perfectbogus.design.patterns.creational.builder.httprequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class HttpRequest {
    private final String url;
    private final HttpMethod method;
    private final Map<String, String> headers;
    private final String body;
    private final int timeoutSeconds;
    private final int maxRetries;

    private HttpRequest(Builder b) {
        this.url = b.url;
        this.method = b.method;
        this.headers = Map.copyOf(b.headers);
        this.body = b.body;
        this.timeoutSeconds = b.timeoutSeconds;
        this.maxRetries = b.maxRetries;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(body);
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public static Builder builder(String url) {
        return new Builder(url);
    }

    public static final class Builder {
        private final String url;
        private HttpMethod method = HttpMethod.GET;
        private Map<String, String> headers = new HashMap<>();
        private String body = null;
        private int timeoutSeconds = 30;
        private int maxRetries = 0;

        private Builder(String url) {
            this.url = url;
        }

        public Builder method(HttpMethod value) {
            this.method = value;
            return this;
        }

        public Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder body(String value) {
            this.body = value;
            return this;
        }

        public Builder timeoutSeconds(int value) {
            this.timeoutSeconds = value;
            return this;
        }

        public Builder maxRetries(int value) {
            this.maxRetries = value;
            return this;
        }

        public HttpRequest build() {
            // pending validations
            return new HttpRequest(this);
        }
    }
}
