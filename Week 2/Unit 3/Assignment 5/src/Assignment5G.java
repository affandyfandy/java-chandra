import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Assignment5G {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        // Thread 1
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                map.put("key" + i, "value" + i);
                System.out.println("Thread 1 added key" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Thread 2
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String value = map.get("key" + i);
                System.out.println("Thread 2 read key" + i + " -> " + value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
