interface ATM {
    public boolean withdraw(int money, double money1);

    public boolean deposit(int money, double money1);

    public double queryBalance(int params);

    public boolean login(String id, String accountID);

    public boolean logout(String logout);

    static void displayBankName() {
        System.out.println("Welcome to Our Bank");
    }
}

class savingAccounts implements ATM {
    private String idATM;
    private String accountID;
    private double balance = 0.0;

    public savingAccounts(String idATM, String accountID) {
        this.idATM = idATM;
        this.accountID = accountID;
    }

    public boolean withdraw(int money, double money1) {
        if (balance >= money1) {
            balance -= money1;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return false;
        }
    }

    public boolean deposit(int money, double money1) {
        balance += money1;
        System.out.println("Deposit successful. Current balance: " + balance);
        return true;
    }

    public double queryBalance(int params) {
        return balance;
    }

    public boolean login(String id, String accountID) {
        if ((id == "00001") && (accountID == "550055")) {
            System.out.println("Login Success");
            return true;
        } else {
            System.out.println("No Account Detected");
            return false;
        }
    }

    public boolean logout(String logout) {
        System.out.println("Logged out successfully.");
        return true;
    }
}

class currentAccounts implements ATM {
    private String idATM;
    private String accountID;
    private double balance = 0.0;

    public currentAccounts(String idATM, String accountID) {
        this.idATM = idATM;
        this.accountID = accountID;
    }

    public boolean withdraw(int money, double money1) {
        if (balance >= money1) {
            balance -= money1;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return false;
        }
    }

    public boolean deposit(int money, double money1) {
        balance += money1;
        System.out.println("Deposit successful. Current balance: " + balance);
        return true;
    }

    public double queryBalance(int params) {
        return balance;
    }

    public boolean login(String id, String accountID) {
        if ((id == "00002") && (accountID == "005500")) {
            System.out.println("Login Success");
            return true;
        } else {
            System.out.println("No Account Detected");
            return false;
        }
    }

    public boolean logout(String logout) {
        System.out.println("Logged out successfully.");
        return true;
    }
}

public class Assignment2D {
    public static void main(String[] args) {
        savingAccounts savingsAccount = new savingAccounts("00001", "550055");
        currentAccounts currentAccount = new currentAccounts("00002", "005500");

        ATM.displayBankName();

        savingsAccount.deposit(500, 500.0);
        System.out.println("Savings Account Balance: " + savingsAccount.queryBalance(0));
        savingsAccount.withdraw(200, 200.0);
        System.out.println("Savings Account Balance: " + savingsAccount.queryBalance(0));
        savingsAccount.logout("exampleUser");

        System.out.println();

        ATM.displayBankName();

        currentAccount.deposit(100, 100.0); // Deposit
        System.out.println("Current Account Balance: " + currentAccount.queryBalance(0));
        currentAccount.withdraw(300, 300.0); // Withdraw
        System.out.println("Current Account Balance: " + currentAccount.queryBalance(0));
        currentAccount.logout("exampleUser");

    }

}
