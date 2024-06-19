import java.util.Scanner;

// Custom unchecked exception class
class Lab2Exception extends RuntimeException {
    public Lab2Exception(String message) {
        super(message);
    }
}

public class Assignment3D {

    public static void main(String[] args) {
        String[] menu = { "Option 1: Start", "Option 2: Load", "Option 3: Save", "Option 4: Settings",
                "Option 5: Exit" };

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a number (1-5) to select a menu option: ");
            int userChoice = scanner.nextInt();

            if (userChoice < 1 || userChoice > 5) {
                throw new Lab2Exception("Invalid menu option selected. Please select a number between 1 and 5.");
            }

            System.out.println(menu[userChoice - 1]);

        } catch (Lab2Exception e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}
