package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;

public class EmailNotificationClient implements NotificationClient {

    @Override
    public void connect() {
        System.out.println("connect from email");
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("send from emailAddress, message: " + message + " to " + recipient);
    }
}
