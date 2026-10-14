package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.User;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notification.NotificationClient;

public class FakeAlertNotifier extends AlertNotifier {

    private final NotificationClient fakeNotificationClient;

    public FakeAlertNotifier(NotificationClient fakeNotificationClient) {
        this.fakeNotificationClient = fakeNotificationClient;
    }

    @Override
    protected NotificationClient createChannel(User user) {
        return fakeNotificationClient;
    }

}
