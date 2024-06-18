@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Abstract method
}

public class Assignment2C {
    public static void main(String[] args) {
        // Using lambda expression to implement the functional interface
        Calculator adder = (a, b) -> a + b;

        // Using the functional interface method
        int result = adder.calculate(10, 5);
        System.out.println("Result of addition: " + result); // Output: 15
    }
}
