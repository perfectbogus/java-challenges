package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;

public class PubSubQueue implements MessageQueue {

    private final String topicName;

    public PubSubQueue(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public void publish(String message) {
        System.out.println("[Google PubSubQueue] Published '" + message + "' to queue " + topicName);

    }
}
