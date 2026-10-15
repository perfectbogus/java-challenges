package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

public interface CloudFactory {
    FileStorage createFileStorage();
    MessageQueue createMessageQueue();
}
