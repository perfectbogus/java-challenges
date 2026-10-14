package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public class FakeNotificationClient implements NotificationClient {

    private boolean connected = false;
    private String sentMessage;
    private String recipient;

    @Override
    public void connect() {
        connected = true;
    }

    @Override
    public void send(String recipient, String message) {
        sentMessage = message;
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getSentMessage() {
        return sentMessage;
    }
}
