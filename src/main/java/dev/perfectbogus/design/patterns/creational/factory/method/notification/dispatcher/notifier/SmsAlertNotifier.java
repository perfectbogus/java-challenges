package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.SmsNotificationClient;

public class SmsAlertNotifier extends AlertNotifier {
    @Override
    protected NotificationClient createChannel(User user) {
        return new SmsNotificationClient(user);
    }
}
