package dev.perfectbogus.design.patterns.creational.builder.email.message.domain;

import dev.perfectbogus.design.patterns.creational.builder.email.message.steps.OptionalStep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private static final String FROM = "noreply@bank.com";
    private static final long MB = 1024L * 1024;

    private static OptionalStep minimal() {
        return Email.builder().from(FROM).to("ana@mail.com").subject("Hi");
    }

    private static OptionalStep withRecipients(int total) {
        OptionalStep builder = Email.builder().from(FROM).to("r0@mail.com").subject("Hi");
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

    @Test
    void allFields_areStored() {
        Attachment pdf = new Attachment("report.pdf", 2 * MB);
        Email email = minimal()
                .to("luis@mail.com")
                .cc("boss@bank.com")
                .bcc("audit@bank.com")
                .body("<p>Hello</p>")
                .attachment(pdf)
                .priority(Priority.HIGH)
                .html(true)
                .build();

        assertAll(
                () -> assertEquals(List.of("ana@mail.com", "luis@mail.com"), email.getTo()),
                () -> assertEquals(List.of("boss@bank.com"), email.getCc()),
                () -> assertEquals(List.of("audit@bank.com"), email.getBcc()),
                () -> assertEquals("<p>Hello</p>", email.getBody()),
                () -> assertEquals(List.of(pdf), email.getAttachments()),
                () -> assertEquals(Priority.HIGH, email.getPriority()),
                () -> assertTrue(email.isHtml())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ana.mail.com", "ana"})
    void to_addressWithoutAt_throws(String badAddress) {
        var toStep = Email.builder().from(FROM);
        assertThrows(IllegalArgumentException.class, () -> toStep.to(badAddress));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ana.mail.com", "ana"})
    void cc_addressWithoutAt_throws(String badAddress) {
        var optionalStep = minimal();
        assertThrows(IllegalArgumentException.class, () -> optionalStep.cc(badAddress));
    }

    @Test
    void duplicateInBcc_throws() {
        var builder = minimal().bcc("ana@mail.com");
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void from_null_throws() {
        assertThrows(NullPointerException.class, () -> Email.builder().from(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blankSubject_throws(String blank) {
        var subjectStep = Email.builder().from(FROM).to("ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> subjectStep.subject(blank));
    }

    @Test
    void exactly50Recipients_succeeds() {
        Email email = withRecipients(50).build();
        assertEquals(50, email.getTo().size() + email.getCc().size());
    }

    @Test
    void recipients51_throws() {
        var builder = withRecipients(51);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void sameAddressInToAndCc_throws() {
        var builder = minimal().cc("ana@mail.com");
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void duplicateDifferentCase_throws() {
        var builder = minimal().cc("Ana@mail.com");
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void noAttachments_succeeds() {
        assertDoesNotThrow(() -> minimal().build());
    }

    @Test
    void attachmentExactly25MB_succeeds() {
        var builder = minimal()
                .attachment(new Attachment("a.pdf", 20 * MB))
                .attachment(new Attachment("b.pdf", 5 * MB));

        assertDoesNotThrow(builder::build);
    }

    @Test
    void attachmentsOver25MB_throws() {
        var builder = minimal()
                .attachment(new Attachment("a.pdf", 25 * MB))
                .attachment(new Attachment("b.pdf", 1 ));

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void getTo_isUnmodifiable() {
        Email email = minimal().build();
        assertThrows(UnsupportedOperationException.class, () -> email.getTo().add("hacker@evil.com"));
    }

    @Test
    void toBuilder_createsModifiedCopy_originalUnchanged() {
        Email original = minimal().build();

        Email copy = original.toBuilder().priority(Priority.HIGH).build();

        assertAll(
                () -> assertEquals(Priority.HIGH, copy.getPriority()),
                () -> assertEquals(original.getSubject(), copy.getSubject()),
                () -> assertEquals(Priority.NORMAL, original.getPriority()),
                () -> assertNotSame(original, copy)
        );
    }

    @Test
    void forward_replacesAllRecipients_andPrefixesSubject() {
        Email original = minimal()
                .cc("boss@bank.com")
                .bcc("audit@bank.com")
                .build();

        Email fwd = original.forward("luis@mail.com");

        assertAll(
                () -> assertEquals(List.of("luis@mail.com"), fwd.getTo()),
                () -> assertTrue(fwd.getCc().isEmpty()),
                () -> assertTrue(fwd.getBcc().isEmpty()),
                () -> assertEquals("Fwd: Hi", fwd.getSubject())
        );
    }

    @Test
    void forward_keepsContent() {
        Attachment pdf = new Attachment("a.pdf", 5 * MB);
        Email original = minimal()
                .body("Quarterly numbers")
                .attachment(pdf)
                .priority(Priority.HIGH)
                .build();

        Email fwd = original.forward("new@mail.com");

        assertAll(
                () -> assertEquals("Quarterly numbers", fwd.getBody()),
                () -> assertEquals(List.of(pdf), fwd.getAttachments()),
                () -> assertEquals(Priority.HIGH, fwd.getPriority())
        );
    }

}