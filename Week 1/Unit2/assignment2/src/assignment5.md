# Implement Thread-Safe Singleton, and Explain

The function of thread safety is to ensure that operations performed by multiple threads at any given time do not result inconsistent outcomes. In the context of Singleton, thread safety refers to the ability to ensure that only one instance of the Singleton class is created, even if multiple threads attempt to create the instance simultaneously.

## Lazy Initialization Singleton

```java
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

public class assignment {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            safethreadSingleton singleton1 = safethreadSingleton.getInstance();
            singleton1.doTask("do Assignment 5");
        });

        Thread t2 = new Thread(() -> {
            safethreadSingleton singleton2 = safethreadSingleton.getInstance();
            singleton2.doTask("do Assignment 6");
        });

        t1.start(); // Outcomes: will Safety-Thread instance created only once.
                    //           do Assignment 5
        t2.start(); // Outcomes: do Assignment 6
    }
}

```

<br>

# Explanation

In _safethreadSingleton's_ class contains a code to do a safe-thread singleton

- Create a static instance variable from safethreadSingleton itself.
- Create a private constructor to prevent the instantiation of the class outside of the class itself
- Create a function static **synchronized** safethreadSingleton getInstance. The thread-safe implementation itself it is on using a synchronized on getInstance method that will perform that only one thread can execute the block of code inside the getInstance() method at a time. This avoids the possibility of a race condition where multiple threads might see that instance is null and attempt to create a new instance concurrently.

In _assignment_ class contains how to check if the thread-safe is working properly by create two thread and run on the same time to see the result. The result it self is showing that the function of synchronized on _getInstance_ prevent the second instance to create another new instance because there is a change that two thread both see instance as a _null_. Without using _synchronized_ on _getInstance_ most likely will perform that two thread will create a new instance

```java
class safethreadSingleton {
    private static safethreadSingleton instance;

    private safethreadSingleton() {
        System.out.println("Safety-Thread instance created only once.");
    }

    public static safethreadSingleton getInstance() {
        if (instance == null) {
            instance = new safethreadSingleton();
        }
        return instance;
    }

    public void doTask(String task) {
        System.out.println(task);
    }
}

public class assignment {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            safethreadSingleton singleton1 = safethreadSingleton.getInstance();
            singleton1.doTask("do Assignment 5");
        });

        Thread t2 = new Thread(() -> {
            safethreadSingleton singleton2 = safethreadSingleton.getInstance();
            singleton2.doTask("do Assignment 6");
        });

        t1.start(); // Outcomes: will Safety-Thread instance created only once.
                    //           do Assignment 5
        t2.start(); // Outcomes: will Safety-Thread instance created only once.
                    //           do Assignment 6
    }
}
```

## Eager Initialization Singleton

```java
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("EagerSingleton instance created only once.");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void doTask(String task) {
        System.out.println(task);
    }
}

public class assignment {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            EagerSingleton singleton1 = EagerSingleton.getInstance();
            singleton1.doTask("do Assignment 5");
        });

        Thread t2 = new Thread(() -> {
            EagerSingleton singleton2 = EagerSingleton.getInstance();
            singleton2.doTask("do Assignment 6");
        });

        t1.start(); // Expected output: "EagerSingleton instance created only once."
                    //                  "do Assignment 5"
        t2.start(); // Expected output: "do Assignment 6"
    }
}
```

<br>

# Explanation

- The singleton instance is created at the time of class loading and stored in the static final variable instance.
- The constructor is private to prevent instantiation from outside the class.
- The getInstance method simply returns the already created instance.
- This approach ensures thread safety without the need for synchronization.

<br>

In this method, the singleton instance is created at the time of class loading. This approach is simple and thread-safe without requiring synchronization, but it may lead to resource wastage if the instance is never used.

## Enum Initialization Singleton

```java
enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {
        System.out.println("EnumSingleton instance created only once.");
    }

    public void doTask(String task) {
        System.out.println(task);
    }
}

public class assignment {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            EnumSingleton singleton1 = EnumSingleton.INSTANCE;
            singleton1.doTask("do Assignment 5");
        });

        Thread t2 = new Thread(() -> {
            EnumSingleton singleton2 = EnumSingleton.INSTANCE;
            singleton2.doTask("do Assignment 6");
        });

        t1.start(); // Expected output: "EnumSingleton instance created only once."
                    //                  "do Assignment 5"
        t2.start(); // Expected output: "do Assignment 6"
    }
}

```

# Explanation

- The singleton instance is represented as an enum constant, INSTANCE.
- The constructor is private by default for enums, ensuring a single instance.
- This approach ensures thread safety, simplicity, and protection against serialization/deserialization issues.

<br>
This is the most robust approach to implement a singleton in Java. Enum values are inherently serializable and the Java language ensures that only one instance of the enum value is created.
