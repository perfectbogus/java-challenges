package dev.perfectbogus.async.stock.price.tracker;

public class PriceAlert {
    private final double threshold;
    private final AlertDirection direction;
    private final Runnable callback;

    public PriceAlert(double threshold, AlertDirection direction, Runnable callback) {
        this.threshold = threshold;
        this.direction = direction;
        this.callback = callback;
    }

    public boolean isTriggered(double price) {
        return direction == AlertDirection.ABOVE
                ? price > threshold
                : price < threshold;
    }

    public void fire() {
        callback.run();
    }
}
