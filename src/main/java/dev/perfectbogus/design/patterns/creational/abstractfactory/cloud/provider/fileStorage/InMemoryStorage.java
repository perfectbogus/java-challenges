package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.fileStorage;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements FileStorage {

    private final Map<String, byte[]> savedFiles = new HashMap<>();

    @Override
    public void save(String fileName, byte[] content) {
        savedFiles.put(fileName, content);
    }

    public Map<String, byte[]> getSavedFiles() {
        return Map.copyOf(savedFiles);
    }
}
