class safethreadSingleton {
    private static safethreadSingleton instance;

    private safethreadSingleton() {
        System.out.println("Safety-Thread instance created only once.");
    }

    public static synchronized safethreadSingleton getInstance() {
        if (instance == null) {
            instance = new safethreadSingleton();
        }
        return instance;
    }

    public void doTask(String task) {
        System.out.println(task);
    }
}

public class assignment5 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            safethreadSingleton singleton1 = safethreadSingleton.getInstance();
            singleton1.doTask("do Assignment 5");
        });

        Thread t2 = new Thread(() -> {
            safethreadSingleton singleton2 = safethreadSingleton.getInstance();
            singleton2.doTask("do Assignment 6");
        });

        t1.start();
        t2.start();
    }
}
