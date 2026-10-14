package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public interface NotificationClient {
    void connect();
    void send(String recipient, String message);
}
