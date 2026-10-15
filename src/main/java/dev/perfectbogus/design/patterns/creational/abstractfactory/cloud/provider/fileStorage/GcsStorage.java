package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.fileStorage;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;

public class GcsStorage implements FileStorage {

    private final String bucket;

    public GcsStorage(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public void save(String fileName, byte[] content) {
        System.out.println("[GCS] Saved " + fileName + "(" + content.length + " bytes) to bucket " + bucket);
    }
}
