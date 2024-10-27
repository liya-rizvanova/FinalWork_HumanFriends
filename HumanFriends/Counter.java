public class Counter implements AutoCloseable {
    private int count;
    private boolean isClosed;

    public Counter() {
        count = 0;
        isClosed = false;
    }

    public void increment() {
        if (isClosed) {
            throw new IllegalStateException("Cannot use Counter after it is closed.");
        }
        count++;
        System.out.println("Counter incremented. Total animals added: " + count);
    }

    @Override
    public void close() throws Exception {
        if (isClosed) {
            throw new IllegalStateException("Counter already closed.");
        }
        isClosed = true;
        System.out.println("Counter closed. Final count: " + count);
    }
}
