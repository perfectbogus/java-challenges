package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.CloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.filestorage.InMemoryStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue.InMemoryQueue;

public class InMemoryCloudFactory implements CloudFactory {

    @Override
    public FileStorage createFileStorage() {
        return new InMemoryStorage();
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new InMemoryQueue();
    }
}
