package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;

public class SqsQueue implements MessageQueue {

    private final String destination;

    public SqsQueue(String destination) {
        this.destination = destination;
    }

    @Override
    public void publish(String message) {
        System.out.println("[AWS SQS] Published '" + message + "' to queue " + destination);
    }
}
