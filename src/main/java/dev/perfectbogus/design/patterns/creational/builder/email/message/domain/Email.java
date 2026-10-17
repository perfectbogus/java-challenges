package dev.perfectbogus.design.patterns.creational.builder.email.message.domain;

import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.FromStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.OptionalStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.SubjectStep;
import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.ToStep;

import java.util.ArrayList;
import java.util.List;

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
        this.to = s.to;
        this.subject = s.subject;
        this.body = s.body;
        this.cc = List.copyOf(s.cc);
        this.bcc = List.copyOf(s.bcc);
        this.attachments = List.copyOf(s.attachments);
        this.priority = s.priority;
        this.html = s.html;
    }

    public static FromStep builder() {
        return new Steps();
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

        private String from;
        private String subject;

        private final List<String> to = new ArrayList<>();
        private final List<String> cc = new ArrayList<>();
        private final List<String> bcc = new ArrayList<>();
        private final List<Attachment> attachments = new ArrayList<>();

        private String body = "";
        private Priority priority = Priority.NORMAL;
        private boolean html = false;

        @Override
        public ToStep from(String from) {
            this.from = from;
            return this;
        }

        @Override
        public Steps to(String to) {
            this.to.add(to);
            return this;
        }

        @Override
        public OptionalStep subject(String subject) {
            this.subject = subject;
            return this;
        }

        @Override
        public OptionalStep body(String body) {
            this.body = body;
            return this;
        }

        @Override
        public OptionalStep cc(String cc) {
            this.cc.add(cc);
            return this;
        }

        @Override
        public OptionalStep bcc(String bcc) {
            this.bcc.add(bcc);
            return this;
        }

        @Override
        public OptionalStep attachment(Attachment a) {
            this.attachments.add(a);
            return this;
        }

        @Override
        public OptionalStep priority(Priority priority) {
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
            // Validation
            return new Email(this);
        }
    }
}
