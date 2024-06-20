import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Assignment4E {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final StringBuilder sharedResource = new StringBuilder();

    // Method to read data from the shared resource
    public void readResource() {
        readLock.lock();
        try {
            // Simulating reading data from the shared resource
            System.out.println("Reading data: " + sharedResource.toString());
        } finally {
            readLock.unlock();
        }
    }

    // Method to write data to the shared resource
    public void writeResource(String data) {
        writeLock.lock();
        try {
            // Simulating writing data to the shared resource
            sharedResource.append(data);
            System.out.println("Written data: " + data);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Assignment4E example = new Assignment4E();

        // Create writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.writeResource("Data" + i + " ");
                try {
                    Thread.sleep(100); // Simulating some delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Create reader threads
        Runnable readTask = () -> {
            for (int i = 0; i < 5; i++) {
                example.readResource();
                try {
                    Thread.sleep(50); // Simulating some delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread readerThread1 = new Thread(readTask, "Reader1");
        Thread readerThread2 = new Thread(readTask, "Reader2");

        // Start the writer and reader threads
        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        // Wait for all threads to finish
        try {
            writerThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
