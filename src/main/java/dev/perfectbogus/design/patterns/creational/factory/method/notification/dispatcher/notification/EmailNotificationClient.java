package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public class EmailNotificationClient implements NotificationClient {

    private final String address = "smtp.bank.com";

    @Override
    public void connect() {
        System.out.println("connect from email");
    }

    @Override
    public void send(String message) {
        System.out.println("send from email, message: " + message);
    }
}
