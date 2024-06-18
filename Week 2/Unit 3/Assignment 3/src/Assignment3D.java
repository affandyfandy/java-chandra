import java.util.Scanner;

// Custom unchecked exception class
class Lab2Exception extends RuntimeException {
    public Lab2Exception(String message) {
        super(message);
    }
}

public class Assignment3D {

    public static void main(String[] args) {
        // Step 1: Create an array menu with 5 elements
        String[] menu = { "Option 1: Start", "Option 2: Load", "Option 3: Save", "Option 4: Settings",
                "Option 5: Exit" };

        Scanner scanner = new Scanner(System.in);

        try {
            // Step 2: User enters the number
            System.out.print("Enter a number (1-5) to select a menu option: ");
            int userChoice = scanner.nextInt();

            // Step 4: Handle unchecked exception for array index out of bounds
            if (userChoice < 1 || userChoice > 5) {
                throw new Lab2Exception("Invalid menu option selected. Please select a number between 1 and 5.");
            }

            // Print the corresponding menu option
            System.out.println(menu[userChoice - 1]);

        } catch (Lab2Exception e) {
            // Handle custom exception
            System.err.println(e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}
