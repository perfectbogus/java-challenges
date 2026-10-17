package dev.perfectbogus.design.patterns.creational.builder.email.message.steps;

import java.util.List;
import java.util.Optional;

public interface BccStep {
    AttachmentsStep bcc(Optional<List<String>> bcc);
}
