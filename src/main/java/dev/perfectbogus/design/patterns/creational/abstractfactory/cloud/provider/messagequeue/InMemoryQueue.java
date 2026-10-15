package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;

import java.util.ArrayList;
import java.util.List;

public class InMemoryQueue implements MessageQueue {

    private final List<String> publishedMessages = new ArrayList<>();

    @Override
    public void publish(String message) {
        publishedMessages.add(message);
    }

    public List<String> getPublishedMessages() {
        return List.copyOf(publishedMessages);
    }
}
