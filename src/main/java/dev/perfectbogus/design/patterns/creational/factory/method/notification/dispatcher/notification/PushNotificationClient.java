package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public class PushNotificationClient implements NotificationClient {

    private final String key = "firebase-key";

    @Override
    public void connect() {
        System.out.println("connect from push");
    }

    @Override
    public void send(String message) {
        System.out.println("send from push, message: " + message);
    }
}
