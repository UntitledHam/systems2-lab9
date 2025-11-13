public class SharedCounter implements Counter {
    private int count = 0;
    public void increment() {
        count++; // This is NOT atomic!
    }
    public int getCount() { 
        return count;
    }
}
