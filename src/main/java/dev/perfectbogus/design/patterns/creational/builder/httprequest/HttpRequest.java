package dev.perfectbogus.design.patterns.creational.builder.httprequest;

import java.util.*;

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

    public int getMaxReti() {
        return 0;
    }

    public static final class Builder {
        private final String url;
        private HttpMethod method = HttpMethod.GET;
        private Map<String, String> headers = new HashMap<>();
        private String body = null;
        private int timeoutSeconds = 30;
        private int maxRetries = 0;
        private EnumSet<HttpMethod> emptyBodyMethodsSet = EnumSet.of(HttpMethod.GET, HttpMethod.DELETE);

        private Builder(String url) {
            this.url = url;
        }

        public Builder method(HttpMethod value) {
            this.method = value;
            return this;
        }

        public Builder header(String name, String value) {
            Objects.requireNonNull(name);
            Objects.requireNonNull(value);
            if (name.isEmpty() || name.isBlank()) throw new IllegalStateException("name must not be null or blank");
            if (value.isEmpty() || value.isBlank()) throw new IllegalStateException("value must not be null or blank");
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
            Objects.requireNonNull(url, "url must not be null");
            Objects.requireNonNull(method, "method must not be null");
            if (!url.matches("https?://.*"))
                throw new IllegalStateException("Url must starts with http:// or https:// :" + url);
            if (timeoutSeconds <= 0) throw new IllegalStateException("timeoutSeconds must be positive");
            if (maxRetries < 0 || maxRetries > 5) throw new IllegalStateException("maxRetries invalid value");
            if (emptyBodyMethodsSet.contains(method) && body != null)
                throw new IllegalStateException("Method GET or DELETE must not have body");
            return new HttpRequest(this);
        }
    }
}
