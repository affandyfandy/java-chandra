import java.util.Scanner;

// Custom exception class
class NoVowelException extends Exception {
    public NoVowelException(String message) {
        super(message);
    }
}

public class Assignment3E {

    // Method to check if the string contains vowels
    public static void checkForVowels(String input) throws NoVowelException {
        if (!input.matches(".*[aeiouAEIOU].*")) {
            throw new NoVowelException("The input string does not contain any vowels.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        try {
            // Call the method to check for vowels
            checkForVowels(userInput);
            System.out.println("The input string contains at least one vowel.");
        } catch (NoVowelException e) {
            // Handle the custom exception
            System.err.println(e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}
