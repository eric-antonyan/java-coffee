import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private final Lock lock = new ReentrantLock();
    private long value;

    public long getValue() {
        this.lock.lock();
        try {
            return this.value;
        } finally {
            this.lock.unlock();
        }
    }

    public void increment() {
        this.lock.lock();

        try {
            this.value++;
        } finally {
            this.lock.unlock();
        }
    }
}
