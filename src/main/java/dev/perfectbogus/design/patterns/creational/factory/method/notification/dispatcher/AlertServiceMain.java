package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.EmailNotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.PushNotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.SmsNotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier.AlertNotifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AlertServiceMain {

    public static void main(String[] args) {
        Map<String, Supplier<? extends NotificationClient>> registry = new HashMap<>();
        registry.put("sms", SmsNotificationClient::new);
        registry.put("push", PushNotificationClient::new);
        registry.put("email", EmailNotificationClient::new);
        final User user = new User("Eduardo", true, true, true);
        final String event = "This Event";

        for (Supplier<? extends NotificationClient> supplier : registry.values()) {
            AlertNotifier alertNotifier = new AlertNotifier(supplier);
            alertNotifier.notifyAlert(user, event);
            System.out.println("Alert sent to " + user.name());
        }

        NotificationClient fakeChannel = new NotificationClient() {

            @Override
            public void connect() {
                System.out.println("connect from fake channel");
            }

            @Override
            public void send(String message) {
                System.out.println("send from fake channel");
            }
        };

        registry.put("fake", () -> fakeChannel);
        AlertNotifier alertNotifier = new AlertNotifier(registry.get("fake"));
        alertNotifier.notifyAlert(user, event);
    }
}
