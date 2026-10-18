package dev.perfectbogus.design.patterns.creational.builder.email.message.domain;

import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.FromStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.OptionalStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.SubjectStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.ToStep;

import java.util.*;

public final class Email {

    private final String from;
    private final List<String> to;
    private final String subject;
    private final String body;
    private final List<String> cc;
    private final List<String> bcc;
    private final List<Attachment> attachments;
    private final Priority priority;
    private final boolean html;

    private Email(Steps s) {
        this.from = s.from;
        this.to = List.copyOf(s.to);
        this.subject = s.subject;
        this.body = s.body;
        this.cc = List.copyOf(s.cc);
        this.bcc = List.copyOf(s.bcc);
        this.attachments = List.copyOf(s.attachments);
        this.priority = s.priority;
        this.html = s.html;
    }

    public OptionalStep toBuilder() {
        return new Steps(this);
    }

    public Email forward(String newRecipient) {
        Steps steps = new Steps(this);
        steps.to.clear();
        steps.cc.clear();
        steps.bcc.clear();
        steps.subject = "Fwd: " + subject;
        return steps.to(newRecipient)
                .build();
    }

    public String getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getCc() {
        return cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isHtml() {
        return html;
    }

    private static final class Steps implements FromStep, ToStep, SubjectStep, OptionalStep {

        private static final long MAX_ATTACHMENTS_BYTES = 25L * 1024 * 1024;

        private String from;
        private String subject;

        private final List<String> to = new ArrayList<>();
        private final List<String> cc = new ArrayList<>();
        private final List<String> bcc = new ArrayList<>();
        private final List<Attachment> attachments = new ArrayList<>();

        private String body = "";
        private Priority priority = Priority.NORMAL;
        private boolean html = false;

        private Steps() {

        }

        private Steps(Email email) {
            this.from = email.getFrom();
            this.subject = email.getSubject();
            this.to.addAll(email.getTo());
            this.cc.addAll(email.getCc());
            this.bcc.addAll(email.getBcc());
            this.attachments.addAll(email.getAttachments());
            this.body = email.getBody();
            this.priority = email.getPriority();
            this.html = email.isHtml();
        }

        @Override
        public ToStep from(String from) {
            requireValidAddress(from);
            this.from = from;
            return this;
        }

        @Override
        public Steps to(String to) {
            requireValidAddress(to);
            this.to.add(to);
            return this;
        }

        @Override
        public OptionalStep subject(String subject) {
            if (subject == null) throw new IllegalStateException("subject must not be null");
            if (subject.isBlank()) throw new IllegalStateException("subject must not be blank");
            this.subject = subject;
            return this;
        }

        @Override
        public OptionalStep body(String body) {
            Objects.requireNonNull(body, "body must not be null");
            this.body = body;
            return this;
        }

        @Override
        public OptionalStep cc(String cc) {
            requireValidAddress(cc);
            this.cc.add(cc);
            return this;
        }

        @Override
        public OptionalStep bcc(String bcc) {
            requireValidAddress(bcc);
            this.bcc.add(bcc);
            return this;
        }

        @Override
        public OptionalStep attachment(Attachment a) {
            Objects.requireNonNull(a, "attachment must not be null");
            this.attachments.add(a);
            return this;
        }

        @Override
        public OptionalStep priority(Priority priority) {
            Objects.requireNonNull(priority, "priority must not be null");
            this.priority = priority;
            return this;
        }

        @Override
        public OptionalStep html(boolean html) {
            this.html = html;
            return this;
        }

        @Override
        public Email build() {
            if (to.size() + cc.size() + bcc.size() > 50) throw new IllegalStateException("Allow up to 50 recipients");
            requireNoDuplicates();
            requireAttachmentsValidSize();
            return new Email(this);
        }

        private void requireValidAddress(String address) {
            Objects.requireNonNull(address, "address must not be null");
            if (!address.contains("@")) throw new IllegalArgumentException("Invalid address: " + address);
        }

        private void requireNoDuplicates() {
            List<String> allRecipients = new ArrayList<>();
            allRecipients.addAll(to);
            allRecipients.addAll(cc);
            allRecipients.addAll(bcc);
            Set<String> set = new HashSet<>();
            for (String w : allRecipients) {
                if (!set.add(w.toLowerCase())) throw new IllegalStateException("Duplicate recipient: " + w);
            }
        }

        private void requireAttachmentsValidSize() {
            if (attachments.stream().mapToLong(Attachment::sizeBytes).sum() > MAX_ATTACHMENTS_BYTES) {
                throw new IllegalStateException("Attachments size must be less than 25 MB");
            }
        }
    }
}
