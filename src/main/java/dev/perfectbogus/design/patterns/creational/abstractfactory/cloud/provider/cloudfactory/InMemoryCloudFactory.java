package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.CloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.filestorage.InMemoryStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue.InMemoryQueue;

public class InMemoryCloudFactory implements CloudFactory {

    private final InMemoryStorage storage;
    private final InMemoryQueue queue;

    public InMemoryCloudFactory() {
        this.storage = new InMemoryStorage();
        this.queue = new InMemoryQueue();
    }


    @Override
    public InMemoryStorage createFileStorage() {
        return storage;
    }

    @Override
    public InMemoryQueue createMessageQueue() {
        return queue;
    }
}
