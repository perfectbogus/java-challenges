package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.CloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.filestorage.GcsStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue.PubSubQueue;

public class GcpCloudFactory implements CloudFactory {

    private final String bucket;
    private final String topicName;

    public GcpCloudFactory(String bucket, String topicName) {
        this.bucket = bucket;
        this.topicName = topicName;
    }

    @Override
    public FileStorage createFileStorage() {
        return new GcsStorage(bucket);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new PubSubQueue(topicName);
    }
}
