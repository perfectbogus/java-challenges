package dev.perfectbogus.design.patterns.creational.builder.email.message.domain;

import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.OptionalStep;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private static final String FROM = "noreply@bank.com";
    private static final long MB = 1024L * 1024;

    private static OptionalStep minimal() {
        return Email.builder().from(FROM).to("ana@mail.com").subject("Hi");
    }

    private static OptionalStep withRecipients(int total) {
        OptionalStep builder = Email.builder().from(FROM).to("r@email.com").subject("Hi");
        for (int i = 1; i < total; i++) {
            builder.cc("r" + i + "@mail.com");
        }
        return builder;
    }

    @Test
    void minimalEmail_hasDefaults() {
        Email email = minimal().build();

        assertAll(
                () -> assertEquals(FROM, email.getFrom()),
                () -> assertEquals(List.of("ana@mail.com"), email.getTo()),
                () -> assertEquals("Hi", email.getSubject()),
                () -> assertEquals("", email.getBody()),
                () -> assertTrue(email.getCc().isEmpty()),
                () -> assertTrue(email.getBcc().isEmpty()),
                () -> assertTrue(email.getAttachments().isEmpty()),
                () -> assertEquals(Priority.NORMAL, email.getPriority()),
                () -> assertFalse(email.isHtml())
        );
    }

}