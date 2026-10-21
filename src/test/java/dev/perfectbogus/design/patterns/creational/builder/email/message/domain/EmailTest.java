package dev.perfectbogus.design.patterns.creational.builder.email.message.domain;

import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.OptionalStep;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private static final String FROM = "noreply@bank.com";
    private static final long MB = 1024L * 1024;

    private static OptionalStep minimal() {
        return Email.builder().from(FROM).to("ana@gmail.com").subject("Hi");
    }

    private static OptionalStep withRecipients(int total) {
        OptionalStep builder = Email.builder().from(FROM).to("r@email.com").subject("Hi");
        for (int i = 1; i < total; i++) {
            builder.cc("r" + i + "@gmail.com");
        }
        return builder;
    }

    @Test
    void createOkEmail_stepBuilder_succeed() {
        Email ok = Email.builder().from("a@bank.com").to("b@mail.com").subject("Hi").build();
        assertNotNull(ok);
    }

    @Test
    void createEmail_bad_failed() {
        Email bad = Email.builder()
                .from("a@bank.com")
                .to("b@mail.com")
                .subject("Hi")
                .attachment(new Attachment("a", 8L))
                .attachment(new Attachment("b", 1L))
                .build();
    }

}