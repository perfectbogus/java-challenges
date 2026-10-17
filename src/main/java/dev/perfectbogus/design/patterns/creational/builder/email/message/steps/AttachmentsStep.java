package dev.perfectbogus.design.patterns.creational.builder.email.message.steps;

import java.util.List;
import java.util.Optional;

public interface AttachmentsStep {
    PriorityStep attachments(Optional<List<byte[]>> attachments);
}
