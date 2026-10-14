package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public class SmsNotificationClient implements NotificationClient {

    @Override
    public void connect() {
        System.out.println("Connect from sms");
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("send from sms, message: " + message + " to " + recipient);
    }

}
