package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification;

public class SmsNotificationClient implements NotificationClient {

    private final String key = "twilio-api-key";

    @Override
    public void connect() {
        System.out.println("Connect from Sms");
    }

    @Override
    public void send(String message) {
        System.out.println("send from sms, message: " + message);
    }

    public String getKey() {
        return this.key;
    }
}
