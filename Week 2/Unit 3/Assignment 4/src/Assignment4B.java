import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment4B {
    private double balance;
    private Lock lock; // Lock to ensure thread safety

    public Assignment4B() {
        balance = 0.0;
        lock = new ReentrantLock();
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        lock.lock(); // Acquire the lock to ensure exclusive access to the balance
        try {
            balance += amount; // Add the deposit amount to the balance
            System.out.println("Deposit: " + amount);
            System.out.println("Balance after deposit: " + balance + "\n");
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        lock.lock(); // Acquire the lock to ensure exclusive access to the balance
        try {
            if (balance >= amount) { // Check if there are sufficient funds
                balance -= amount; // Subtract the withdrawal amount from the balance
                System.out.println("Withdrawal: " + amount);
                System.out.println("Balance after withdrawal: " + balance + "\n");
            } else {
                System.out.println("Try to Withdraw: " + amount);
                System.out.println("Insufficient funds. Withdrawal cancelled.\n");
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    // Main method to test the deposit and withdraw functionality
    public static void main(String[] args) {
        Assignment4B account = new Assignment4B(); // Create a new account

        // Create threads for deposits and withdrawals
        Thread depositThread1 = new Thread(() -> account.deposit(1000));
        Thread depositThread2 = new Thread(() -> account.deposit(300));
        Thread withdrawalThread1 = new Thread(() -> account.withdraw(150));
        Thread withdrawalThread2 = new Thread(() -> account.withdraw(1200));

        // Start the threads
        depositThread1.start();
        depositThread2.start();
        withdrawalThread1.start();
        withdrawalThread2.start();
    }
}
