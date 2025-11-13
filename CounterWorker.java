public class CounterWorker implements Runnable {
    private Counter counter;
    private int increments;

    public CounterWorker(Counter counter, int increments) {
        this.counter = counter;
        this.increments = increments;
    }
    @Override public void run() {
        for (int i = 0; i < increments; i++) {
            counter.increment();
        }
    }
}
