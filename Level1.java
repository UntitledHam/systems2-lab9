public class Level1 {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();
        int numThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numThreads];
        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new CounterWorker(counter, incrementsPerThread));
            threads[i].start();
        }
        // Wait for all threads to complete
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Expected count: " + (numThreads * incrementsPerThread));
        System.out.println("Actual count: " + counter.getCount());
    }
}
