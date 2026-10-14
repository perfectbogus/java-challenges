package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class AlertNotifier {

    private final Supplier<? extends NotificationClient> notificationFactory;

    public AlertNotifier(Supplier<? extends NotificationClient> notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    public void notifyAlert(User user, String event) {
        String message = "[ALERT] " + event + " at " + LocalDateTime.now();
        NotificationClient notificationClient = notificationFactory.get();
        notificationClient.connect();
        notificationClient.send(message);
        System.out.println("Alert sent to " + user.name());
    }
}
