package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

import java.util.Objects;

public class InvoiceService {

    private final FileStorage storage;
    private final MessageQueue queue;

    public InvoiceService(CloudFactory factory) {
        this.storage = factory.createFileStorage();
        this.queue = factory.createMessageQueue();
    }

    public void uploadInvoice(String invoiceId, byte[] pdf) {
        Objects.requireNonNull(invoiceId, "invoiceId must be not null");
        Objects.requireNonNull(pdf, "pdf must be not null");
        storage.save(invoiceId + ".pdf", pdf);
        queue.publish("Invoice uploaded: " + invoiceId);
    }
}
