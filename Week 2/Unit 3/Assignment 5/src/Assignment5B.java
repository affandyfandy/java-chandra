import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment5B {
    public static void main(String[] args) {
        // Create an ArrayList and add some elements
        List<String> arrayList = new ArrayList<>();
        arrayList.add("JAVA SECURITY");
        arrayList.add("JAVA MANDATORY");
        arrayList.add("JAVA SCANNER");
        arrayList.add("JAVA LION");
        arrayList.add("JAVA SYNTAX");

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the index of the element they want to retrieve
        System.out.print("Enter the index : ");
        int index = scanner.nextInt();

        // Retrieve and print the element at the specified index
        if (index >= 0 && index < arrayList.size()) {
            String element = arrayList.get(index);
            System.out.println("Element at index " + index + ": " + element);
        } else {
            System.out
                    .println("Index out of bounds. Please enter a valid index between 0 and " + (arrayList.size() - 1));
        }

        // Close the scanner
        scanner.close();
    }
}
