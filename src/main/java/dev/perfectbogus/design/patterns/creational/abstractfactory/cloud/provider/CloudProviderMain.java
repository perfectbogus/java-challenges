package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.AwsCloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.GcpCloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.InMemoryCloudFactory;

public class CloudProviderMain {
    public static void main(String[] args) {
        CloudFactory factory;
        if (args[0].equalsIgnoreCase("amazon")) {
            factory = new AwsCloudFactory("aws-bucket", "aws-event");
        } else if (args[0].equalsIgnoreCase("google")) {
            factory = new GcpCloudFactory("gcp-bucker", "gcp-queue");
        } else if (args[0].equalsIgnoreCase("inmemory")) {
            factory = new InMemoryCloudFactory();
        } else {
            throw new IllegalArgumentException("args[0] is not valid cloud provider");
        }

        InvoiceService service = new InvoiceService(factory);
        byte[] content = new byte[128];
        service.uploadInvoice("inv-123", content);

    }
}
