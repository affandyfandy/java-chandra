import java.util.Scanner;

class NoVowelException extends Exception {
    public NoVowelException(String message) {
        super(message);
    }
}

public class Assignment3E {
    public static void checkForVowels(String input) throws NoVowelException {
        boolean hasVowel = false;
        for (int i = 0; i < input.length(); i++) {
            char temp = Character.toLowerCase(input.charAt(i));
            if (temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u') {
                hasVowel = true;
                break;
            }
        }
        if (!hasVowel) {
            throw new NoVowelException("The input string does not contain any vowels.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        try {
            checkForVowels(userInput);
            System.out.println("The input string contain vowel.");
        } catch (NoVowelException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
