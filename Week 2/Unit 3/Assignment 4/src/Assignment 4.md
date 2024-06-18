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
