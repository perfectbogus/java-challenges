package dev.perfectbogus.design.patterns.creational.builder.email.message.steps;

import dev.perfectbogus.design.patterns.creational.builder.email.message.domain.Attachment;
import dev.perfectbogus.design.patterns.creational.builder.email.message.domain.Email;
import dev.perfectbogus.design.patterns.creational.builder.email.message.domain.Priority;

public interface OptionalStep {
    OptionalStep to(String to);
    OptionalStep body(String body);
    OptionalStep cc(String cc);
    OptionalStep bcc(String bcc);
    OptionalStep attachment(Attachment a);
    OptionalStep priority(Priority priority);
    OptionalStep html(boolean html);
    Email build();
}
