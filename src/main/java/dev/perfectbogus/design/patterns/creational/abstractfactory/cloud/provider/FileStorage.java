package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

public interface FileStorage {
    void save(String fileName, byte[] content);
}
