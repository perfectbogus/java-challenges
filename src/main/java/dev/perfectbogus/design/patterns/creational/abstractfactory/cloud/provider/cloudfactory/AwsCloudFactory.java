package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.CloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.FileStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.filestorage.S3Storage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue.SqsQueue;

public class AwsCloudFactory implements CloudFactory {

    private final String bucket;
    private final String topicName;

    public AwsCloudFactory(String bucket, String topicName) {
        this.bucket = bucket;
        this.topicName = topicName;
    }

    @Override
    public FileStorage createFileStorage() {
        return new S3Storage(bucket);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new SqsQueue(topicName);
    }
}
