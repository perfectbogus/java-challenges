package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.InMemoryCloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.filestorage.InMemoryStorage;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.messagequeue.InMemoryQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {

    private InMemoryStorage storage;
    private InMemoryQueue queue;
    private InvoiceService service;

    @BeforeEach
    void setUp() {
        InMemoryCloudFactory factory = new InMemoryCloudFactory();
        storage = factory.createFileStorage();
        queue = factory.createMessageQueue();
        service = new InvoiceService(factory);
    }

    @Test
    void uploadInvoice_savesPdfWithInvoiceIdAsFileName() {
        final byte[] pdf = {1, 2, 3};
        final String invoicedId = "INV-123";

        service.uploadInvoice(invoicedId, pdf);

        Map<String, byte[]> savedFiles = storage.getSavedFiles();
        assertTrue(savedFiles.containsKey(invoicedId+".pdf"));
        assertArrayEquals(pdf, savedFiles.get(invoicedId + ".pdf"));
    }

    @Test
    void uploadInvoice_publishesExactlyOneUploadEvent() {
        final byte[] pdf = {1, 2, 3};
        final String invoiceId = "INV-123";

        service.uploadInvoice(invoiceId, pdf);

        List<String> messages = queue.getPublishedMessages();
        assertEquals(1, messages.size());
        assertEquals("Invoice uploaded: " + invoiceId, messages.get(0));
    }

    @Test
    void uploadInvoice_withNullId_throwsAndSavesNothing() {
        assertThrows(NullPointerException.class, () -> service.uploadInvoice(null, new byte[]{1, 2, 3}));
        assertTrue(queue.getPublishedMessages().isEmpty());
    }

}