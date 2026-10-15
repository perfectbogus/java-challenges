package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;

public class SqsQueue implements MessageQueue {

    private final String topicName;

    public SqsQueue(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public void publish(String message) {
        System.out.println("[AWS SQS] Published '" + message + "' to queue " + topicName);
    }
}
