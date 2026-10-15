package dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider;

import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.AwsCloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.GcpCloudFactory;
import dev.perfectbogus.design.patterns.creational.abstractfactory.cloud.provider.cloudfactory.InMemoryCloudFactory;

public class CloudProviderMain {
    public static void main(String[] args) {
        CloudFactory factory = switch (args[0].toLowerCase()) {
            case "aws" -> new AwsCloudFactory("aws-bucket", "aws-events");
            case "gcp" -> new GcpCloudFactory("gcp-bucket", "gcp-events");
            case "inmemory" -> new InMemoryCloudFactory();
            default -> throw new IllegalArgumentException("Unknown provider: " + args[0]);
        };

        InvoiceService service = new InvoiceService(factory);
        byte[] content = new byte[128];
        service.uploadInvoice("inv-123", content);

    }
}
