package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.PushNotificationClient;

public class PushAlertNotifier extends AlertNotifier {
    @Override
    protected NotificationClient createChannel(User user) {
        return new PushNotificationClient();
    }
}
