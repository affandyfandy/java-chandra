# Assignment 5

## CopyOnWriteArrayList vs ArrayList

In Java, CopyOnWriteArrayList and ArrayList are both implementations of the List interface, but they have distinct characteristics and are used in different scenarios.

- ArrayList

  - Underlying Data Structure:

    - Uses a resizable array to store elements.
    - When elements are added or removed, the underlying array may be resized, which involves creating a new array and copying elements.

  - Performance:

    - Fast for read operations (O(1) time complexity for accessing an element by index).
    - Additions and deletions are generally fast but can be slow if resizing the array is necessary.
    - Not thread-safe. Multiple threads modifying an ArrayList concurrently may result in undefined behavior.

  - Use Case:

        - Suitable for single-threaded environments or when external synchronization is provided for multi-threaded use.
        - Preferred when there are frequent modifications (adds, removes) and reads are not extremely frequent or performance-critical.

- CopyOnWriteArrayList

  - Underlying Data Structure:

    - Also uses a resizable array, but creates a fresh copy of the array with each modification (add, remove, set operations).
    - Ensures that any modification does not affect any ongoing read operations.

<br>

- Performance:

  - Read operations are typically faster than ArrayList because they are not synchronized and can occur without locks.
  - Write operations (add, remove, set) are slower because they involve creating a new copy of the array.
  - Thread-safe. Multiple threads can read from the list concurrently without additional synchronization. Writes are thread-safe but costly.

<br>

- Use Case:

  - Suitable for concurrent environments where reads are more frequent than writes.

  - Ideal for lists that are infrequently modified but frequently read.

<br>

Here is the implement for both arraylist and CopyOnWriteArrayList

_Array List_

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        for (String item : list) {
            System.out.println(item);
        }
    }
}
```

_CopyOnWriteArrayList_

```java
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        for (String item : list) {
            System.out.println(item);
        }
    }
}
```

## Program retrive a value from an index Input

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment4B {
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
```
