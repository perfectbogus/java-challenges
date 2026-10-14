package dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher;

import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier.AlertNotifier;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier.PushAlertNotifier;
import dev.perfectbogus.design.patterns.creational.factory.method.notification.dispatcher.notifier.SmsAlertNotifier;

import java.util.ArrayList;
import java.util.List;

public class AlertServiceMain {

    public static void main(String[] args) {
        final User user = new User(
                "Eduardo",
                "email@example.com",
                "5512345678",
                "twilio-api-key");

        final String event = "This Event";

        List<AlertNotifier> alertNotifiers = new ArrayList<>();
        alertNotifiers.add(new SmsAlertNotifier());
        alertNotifiers.add(new PushAlertNotifier());
        alertNotifiers.add(new AlertNotifier());

        for (AlertNotifier alert : alertNotifiers) {
            alert.notifyAlert(user, event);
        }
    }
}
