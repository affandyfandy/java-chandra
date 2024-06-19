# Assignment 3

## Research and explain try-with-resources

In Java, the Try-with-resources statement is a try statement that declares one or more resources in it. A resource is an object that must be closed once your program is done using it. For example, a File resource or a Socket connection resource. The try-with-resources statement ensures that each resource is closed at the end of the statement execution. If we don’t close the resources, it may constitute a resource leak and also the program could exhaust the resources available to it.

You can pass any object as a resource that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable.

By this, now we don’t need to add an extra finally block for just passing the closing statements of the resources. The resources will be closed as soon as the try-catch block is executed.

```java
try(declare resources here) {
    // use resources
}
catch(FileNotFoundException e) {
    // exception handling
}
```

When it comes to exceptions, there is a difference in try-catch-finally block and try-with-resources block. If an exception is thrown in both try block and finally block, the method returns the exception thrown in finally block.

For try-with-resources, if an exception is thrown in a try block and in a try-with-resources statement, then the method returns the exception thrown in the try block. The exceptions thrown by try-with-resources are suppressed, i.e. we can say that try-with-resources block throws suppressed exceptions.

**Case 1: Single resource**

```java
import java.io.*;

// Class
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        try (
            FileOutputStream fos
            = new FileOutputStream("gfgtextfile.txt")) {

            String text
                = "Hello World. This is my java program";

            byte arr[] = text.getBytes();

            fos.write(arr);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(
            "Resource are closed and message has been written into the gfgtextfile.txt");
    }
}
```

**Case 2: Multiple resources**

```java
import java.io.*;

// Class
class GFG {

    public static void main(String[] args)
    {

        try (FileOutputStream fos
             = new FileOutputStream("outputfile.txt");
             BufferedReader br = new BufferedReader(
                 new FileReader("gfgtextfile.txt"))) {

            String text;

            while ((text = br.readLine()) != null) {

                byte arr[] = text.getBytes();

                fos.write(arr);
            }

            System.out.println(
                "File content copied to another one.");
        }

        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(
            "Resource are closed and message has been written into the gfgtextfile.txt");
    }
}
```

## Throw and Throws

```java
public class ThrowThrowsExample {

    // Method that declares it can throw an IOException
    public void readFile(String fileName) throws IOException {
        if (fileName == null) {
            // Explicitly throwing a NullPointerException
            throw new NullPointerException("Filename cannot be null");
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    public static void main(String[] args) {
        ThrowThrowsExample example = new ThrowThrowsExample();
        try {
            // Attempt to read a file (this might throw an IOException)
            example.readFile(null);
        } catch (IOException e) {
            // Handling IOException
            System.err.println("An IOException was caught: " + e.getMessage());
        } catch (NullPointerException e) {
            // Handling NullPointerException
            System.err.println("A NullPointerException was caught: " + e.getMessage());
        }
    }
}

```

### Explanation

- Throws
  - Location: In the method signature of readFile.
  - Purpose: Indicates that readFile might throw an IOException.
  - Usage: public void readFile(String fileName) throws IOException.

<br>

- Throw
  - Location: Inside the readFile method.
  - Purpose: Explicitly throws a NullPointerException when fileName is null.
  - Usage: throw new NullPointerException("Filename cannot be null");.

## Assignment LAB 1

```java
import java.io.*;

public class Assignment3C {

    public static void main(String[] args) {
        String inputFileName = "test1.txt";
        String outputFileName = "test2.txt";

        // Using try-with-resources to ensure resources are closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Print text to console
                System.out.println(line);

                // Write text to output file
                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
```

## Assignment LAB 2

```java
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
```

## Assignment LAB 3

```java
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
```
