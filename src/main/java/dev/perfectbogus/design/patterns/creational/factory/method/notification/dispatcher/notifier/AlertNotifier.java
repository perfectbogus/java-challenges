package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.EmailNotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;

import java.time.LocalDateTime;

public class AlertNotifier {

    public final void notifyAlert(User user, String event) {
        String message = "[ALERT] " + event + " at " + LocalDateTime.now();
        NotificationClient notificationClient = createChannel(user);
        notificationClient.connect();
        notificationClient.send(user.name(), message);
        System.out.println("Alert sent to " + user.name());
    }

    protected NotificationClient createChannel(User user) {
        return new EmailNotificationClient();
    }
}
