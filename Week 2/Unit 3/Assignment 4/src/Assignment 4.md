# Assignment 4

## Deadlock in Java

Deadlock is a situation in concurrent programming where two or more threads are blocked forever, waiting for each other to release resources. In a deadlock scenario, each thread holds a resource that another thread needs, while it waits for the resource that the first thread holds. As a result, neither thread can proceed, leading to a complete halt in the execution of both threads.

Here the example of java deadlock with threads

```java
public class DeadlockExample {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Holding resource 1 and 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding resource 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 2: Holding resource 2 and 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

```

In code above, thread1 locks resource1 and tries to lock resource2, while thread2 locks resource2 and tries to lock resource1. If both threads start executing simultaneously, they may reach a state where thread1 is waiting for resource2 to be released by thread2, and thread2 is waiting for resource1 to be released by thread1. Thus, both threads are stuck waiting indefinitely, causing a deadlock.

Deadlocks can be prevented by carefully managing how threads acquire and release resources.

- Lock Ordering: Ensure that all threads acquire locks in the same order. This prevents cyclic dependencies where each thread is waiting for a resource held by another thread.

- Lock Timeout: Use tryLock() with a timeout instead of synchronized blocks. This allows threads to attempt to acquire a lock but to give up if the lock is not acquired within a specified time period.

- Resource Allocation Graph: Use a resource allocation graph and ensure there are no cycles. This can be applied in more complex scenarios involving multiple resources and threads.

- Avoid Nested Locks: If possible, avoid nested locks. If you need multiple resources, acquire them in a consistent order throughout your application.

- Deadlock Detection: Implement deadlock detection algorithms, although prevention is generally preferred over detection due to its complexity.

## Creates a bank account with concurrent deposits and withdrawals using threads.

When creating a code for make sure there is no race between multiple withdrawals and multiple deposits java gave us ReentrantLock (lock) which is for ensures that only one thread can execute the deposit or withdraw methods at a time, preventing race conditions and ensuring that operations on balance are consistent and correct.

Here is the example for the code Bank Account

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment4B {
    private double balance;
    private Lock lock;

    public Assignment4B() {
        balance = 0.0;
        lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposit: " + amount);
            System.out.println("Balance after deposit: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal: " + amount);
                System.out.println("Balance after withdrawal: " + balance);
            } else {
                System.out.println("Try to Withdraw: " + amount);
                System.out.println("Insufficient funds. Withdrawal cancelled.");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Assignment4B account = new Assignment4B();

        Thread depositThread1 = new Thread(() -> account.deposit(1000));
        Thread depositThread2 = new Thread(() -> account.deposit(300));
        Thread withdrawalThread1 = new Thread(() -> account.withdraw(150));
        Thread withdrawalThread2 = new Thread(() -> account.withdraw(1200));

        depositThread1.start();
        depositThread2.start();
        withdrawalThread1.start();
        withdrawalThread2.start();
    }
}
```

### Explanation

- BankAccount Class (Assignment4B):

  - Instance Variables:

    - balance: Represents the current balance of the bank account.
    - lock: A ReentrantLock instance used to synchronize access to the balance variable. This ensures that only one thread can modify the balance at a time, preventing concurrent access issues.

    <br>

  - Constructor (Assignment4B()):

    - Initializes the balance to 0.0 (assuming a new bank account starts with zero balance).
    - Initializes the lock with a new ReentrantLock() instance.

    <br>

  - deposit(double amount) Method:

    - Acquires the lock (lock.lock()) to ensure exclusive access to the balance.
    - Increases the balance by the specified amount.
    - Prints a message indicating the deposit amount and the updated balance.
    - Releases the lock (lock.unlock()) in a finally block to ensure it's always released, even if an exception occurs.

    <br>

  - withdraw(double amount) Method:

    - Acquires the lock (lock.lock()) to ensure exclusive access to the balance.
    - Checks if the account has sufficient funds (balance >= amount):
      - If true, decreases the balance by the specified amount.
      - Prints a message indicating the withdrawal amount and the updated balance.
      - If false (insufficient funds), prints a message indicating the attempted withdrawal amount and that the withdrawal is cancelled.
      - Releases the lock (lock.unlock()) in a finally block.

<br>

- Main Method (main()):
  - Creates an instance of Assignment4B named account.
  - Creates four separate threads (depositThread1, depositThread2, withdrawalThread1, withdrawalThread2), each performing a deposit or withdrawal operation on the account object.
  - Starts all threads using start(). Each thread executes its respective deposit or withdrawal operation concurrently.

## Create a Bank Account with Deposits and Withdrawals using threads

Implementing threads on bank account case can be very usefull, one of the function for implementing threads is to avoid any activities when there is one activity who is still running.

Here is the code below to implements Bank Account using threads

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment4B {
    private double balance;
    private Lock lock;

    public Assignment4B() {
        balance = 0.0;
        lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposit: " + amount);
            System.out.println("Balance after deposit: " + balance + "\n");
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal: " + amount);
                System.out.println("Balance after withdrawal: " + balance + "\n");
            } else {
                System.out.println("Try to Withdraw: " + amount);
                System.out.println("Insufficient funds. Withdrawal cancelled.\n");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Assignment4B account = new Assignment4B();

        Thread depositThread1 = new Thread(() -> account.deposit(1000));
        Thread depositThread2 = new Thread(() -> account.deposit(300));
        Thread withdrawalThread1 = new Thread(() -> account.withdraw(150));
        Thread withdrawalThread2 = new Thread(() -> account.withdraw(1200));

        depositThread1.start();
        depositThread2.start();
        withdrawalThread1.start();
        withdrawalThread2.start();
    }
}
```

On this code above we use multiple thread to run each activity such as create a thread for deposit and also withdrawal. In this code above also use Lock and ReentrantLock, both of these method used for make sure there is no other activity before the first activity is done and clear, and this can be annotation with,

```java
before doing activity
lock.lock()

after doing activity
lock.unlock()
```

On those code above lock.lock() any activity regarding to those accountID cannot be bothered unless the code is finished with using lock.unloack() method.

## Sort Array using Threads

Sorting of an arrays of Integer can be implements with a quick of running time, but in some cases if we have a tons of an array it can be very long to run. Using threads can be used to enhance the performance of sorting an array of integers by enabling parallel processing. The function of threads in sorting involves dividing the sorting task into smaller, manageable chunks that can be processed simultaneously across multiple threads. Here is the benefits of using threads for sorting arrays of integer,

- Reduced Time Complexity: By sorting subarrays in parallel, the overall sorting time can be significantly reduced compared to a single-threaded approach.
- Handling Large Arrays: Sorting very large arrays can be more feasible as the workload is distributed among several threads.
- Divide and Conquer: The array is divided into smaller subarrays, and each subarray is sorted independently by different threads.

here is also the code for sorting an arrays using threads

```java
import java.util.Arrays;

public class Assignment4C {
    public void multiThreadSort(int threads, int[] arr, int start, int stop) {
        if (threads > 1) {
            int midpoint = partition(arr, start, stop);
            Thread t1 = new Thread(() -> multiThreadSort(threads - 1, arr, start, midpoint));
            Thread t2 = new Thread(() -> multiThreadSort(threads - 1, arr, midpoint + 1, stop));

            // Start the threads
            t1.start();
            t2.start();

            try {
                // Ensure that the main thread waits for t1 and t2 to complete
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Sort the subarray if only one thread is available
            Arrays.sort(arr, start, stop);
        }
    }

    public int partition(int[] arr, int start, int stop) {
        int pivot = arr[stop - 1];
        int i = start - 1;
        for (int j = start; j < stop - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[stop - 1];
        arr[stop - 1] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 99, 62, 0, 33, 1 };
        int threads = 2;
        Assignment4C sorter = new Assignment4C();
        sorter.multiThreadSort(threads, arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}


```

The result of that code would be like below

                                [0, 1, 3, 4, 5, 33, 62, 99]

Method multiThreadSort is to give multiple threads to perform sorting based on array given and also to get the index where is the midpoint as a divider.

Method partition will get three params find the midpoint check that will used for the method of multiThreadSort will use to activated the thread base one the partition (divided by half).

## Noticeable things for Using Multi Threads

After doing bank account cases, and sorting an array using multiple threads, there is a couple of consideration for choosing multiple threads and there is,

- **Parallelism:** Multithreading introduces parallelism to your program. If there are parallel paths (parts that don’t depend on each other’s results), you can exploit multiple threads. Examples include processing of sorting an array of Integer.
- **Heavy CPU Tasks:** Use multithreading for CPU-bound tasks. For instance, when you want to perform heavy operations without blocking the program flow, multithreading allows you to achieve this. UIs often use background threads for heavy processing while keeping the UI responsive1.

## ReadWriteLock for Read/Write access to Share
