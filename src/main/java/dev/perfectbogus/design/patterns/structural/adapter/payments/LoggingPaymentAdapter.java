package dev.perfectbogus.design.patterns.structural.adapter.payments;


import java.util.List;

// Task 6 — Adapter that wraps another PaymentProcessor and adds logging.
// This is an adapter wrapping an adapter — the wrapped processor does the real work.
// Every call is logged before and after: log entry before → delegate → log entry after.
public class LoggingPaymentAdapter implements PaymentProcessor {

    // TODO: private field for the wrapped PaymentProcessor
    // TODO: private List<String> to accumulate log entries

    // TODO: constructor accepting PaymentProcessor processor
    //       throw IllegalArgumentException("Processor cannot be null") if null
    public LoggingPaymentAdapter(PaymentProcessor processor) {
        // TODO: implement
    }

    // TODO: log "Processing payment: currency=X amount=Y"
    //       delegate to wrapped processor
    //       log "Payment result: true/false"
    //       return the result
    @Override
    public boolean processPayment(String currency, double amount) {
        // TODO: implement
        return false;
    }

    // TODO: log "Refunding payment: transactionId=X"
    //       delegate to wrapped processor
    //       log "Refund result: true/false"
    //       return the result
    @Override
    public boolean refundPayment(String transactionId) {
        // TODO: implement
        return false;
    }

    // TODO: return an unmodifiable List<String> of all log entries in order
    public List<String> getLogs() {
        // TODO: implement
        return null;
    }
}
