package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.fileStorage;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;

public class S3Storage implements FileStorage {

    private final String bucket;

    public S3Storage(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public void save(String fileName, byte[] content) {
        System.out.println("[AWS S3] Saved " + fileName + "(" + content.length + " bytes) to bucket " + bucket);
    }
}
