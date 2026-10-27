package dev.perfectbogus.design.patterns.creational.prototype.document.template;

import java.nio.ByteBuffer;

public final class Logo {
    private final byte[] bytes;

    public Logo(byte[] bytes) {
        this.bytes = bytes.clone();
    }

    public int size() {
        return bytes.length;
    }

    public byte[] getBytes() {
        return bytes.clone();
    }

    public ByteBuffer asReadOnlyBuffer() {
        return ByteBuffer.wrap(bytes).asReadOnlyBuffer();
    }
}
