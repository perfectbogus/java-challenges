package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messageQueue;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.MessageQueue;

import java.util.ArrayList;
import java.util.List;

public class InMemoryQueue implements MessageQueue {

    private List<String> publishedMessages = new ArrayList<>();

    @Override
    public void publish(String message) {
        publishedMessages.add(message);
    }

    public List<String> getPublishedMessages() {
        return List.copyOf(publishedMessages);
    }
}
