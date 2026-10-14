package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;

public class PushNotificationClient implements NotificationClient {

    @Override
    public void connect() {
        System.out.println("connect from push");
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("send from push, message: " + message + " to " + recipient);
    }
}
