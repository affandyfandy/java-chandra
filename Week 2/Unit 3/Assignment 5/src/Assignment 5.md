# Assignment 5

## ArrayList vs Linkedlist

- **ArrayList:** ArrayList is a resizable array implementation of the List interface. It provides methods to manipulate the size of the array that is used internally to store the list.

  - Internal Structure: It uses a dynamic array to store the elements.
  - Access Time: Provides O(1) time complexity for accessing elements by index because it allows direct access via the array.
  - Insertion and Deletion: Inserting or deleting elements (except at the end of the list) can be costly, with a time complexity of O(n) in the worst case, because it may require shifting elements.
  - Memory Usage: Generally uses less memory per element than a LinkedList because it only stores the actual object references without additional pointers.

<br>

- **LinkedList:** LinkedList is a doubly-linked list implementation of the List and Deque interfaces. It allows for efficient insertions and deletions.

  - Internal Structure: It uses a doubly-linked list to store the elements. Each element (node) contains a reference to the previous and next element.
  - Access Time: Provides O(n) time complexity for accessing elements by index, as it requires traversing the list from the beginning or end to the desired position.
  - Insertion and Deletion: Inserting or deleting elements (especially at the beginning or the end of the list) is efficient, with a time complexity of O(1) for these operations.
  - Memory Usage: Uses more memory per element compared to an ArrayList because it needs to store two additional pointers (previous and next references) for each element.

<br>

Table shown below is the different between ArrayList and LinkedList

| No  | ArrayList                                                                                                                             | LinkedList                                                                                                                            |
| --- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| 1   | ArrayList internally uses a dynamic array to store the elements.                                                                      | LinkedList internally uses a doubly linked list to store the elements.                                                                |
| 2   | Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list, so no bit shifting is required in memory. | Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list, so no bit shifting is required in memory. |
| 3   | An ArrayList class can act as a list only because it implements List only.                                                            | LinkedList class can act as a list and queue both because it implements List and Deque interfaces.                                    |
| 4   | ArrayList is better for storing and accessing data.                                                                                   | LinkedList is better for manipulating data.remove at beginning/end                                                                    |

below is the simple implements ArrayList and LinkedList

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Assignment5A {
    public static void main(String[] args) {
        // ArrayList example
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Horse");
        arrayList.add("Cat");
        arrayList.add("Dog");
        System.out.println("ArrayList: " + arrayList);

        // LinkedList example
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.add("Cat");
        linkedList.add("Horse");
        System.out.println("LinkedList: " + linkedList);
    }
}
```

And here is the Output

                            ArrayList: [Horse, Cat, Dog]
                            LinkedList: [Dog, Cat, Horse]

## HashSet vs TreeSet vs LinkedHashSet

- **HashSet:** HashSet is a class in Java that implements the Set interface. It uses a hash table for storage. Here are the key points:

  - HashSet stores elements using a hash table, which allows for fast insertion, deletion, and retrieval of elements.
  - Order: There is no guarantee of the order of elements stored in a HashSet. The order can change when elements are added or removed.
  - Null Element: HashSet allows at most one null element.
  - Performance: Offers constant-time performance for basic operations (add, remove, contains) on average. However, performance can degrade to O(n) in the worst case due to collisions in hash table buckets.
  - Usage: Ideal when you need a high-performance set and the order of elements does not matter.

<br>

- **TreeSet:** TreeSet is another class in Java that implements the SortedSet interface. It uses a Red-Black tree for storage. Key points include:

  - TreeSet stores elements in a sorted order, either natural ordering or according to a specified comparator.
  - Order: Maintains elements in sorted order, which allows for efficient traversal of elements in sorted order.
  - Null Element: TreeSet does not allow null elements. It throws a NullPointerException if you attempt to add null.
  - Performance: Offers O(log n) time complexity for add, remove, and contains operations due to the balanced tree structure.

  - Usage: Useful when you need a set that maintains elements in sorted order or when you need to define a custom sorting order using a comparator.

<br>

- **LinkedHashSet:** LinkedHashSet is a subclass of HashSet that maintains insertion order. Here are the key points:

  - LinkedHashSet is similar to HashSet but additionally maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, which is the order in which elements were inserted into the set.
  - Order: Maintains elements in the order they were inserted, providing predictable iteration order (insertion order).
  - Null Element: LinkedHashSet allows at most one null element, similar to HashSet.
  - Performance: Offers O(1) time complexity for add, remove, and contains operations, similar to HashSet, with a slight overhead due to maintaining the linked list.
  - Usage: Useful when you need a set that maintains the order of insertion and offers performance characteristics similar to HashSet.

Here is the simple implementation about HashSet, TreeSet, and LinkedHashSet

```java
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Assignment5AII {
    public static void main(String[] args) {
        // HashSet example
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Cat");
        hashSet.add("Dog");
        hashSet.add("Bird");
        System.out.println("HashSet: " + hashSet);

        // TreeSet example
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Cat");
        treeSet.add("Dog");
        treeSet.add("Bird");
        System.out.println("TreeSet: " + treeSet);

        // LinkedHashSet example
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Cat");
        linkedHashSet.add("Dog");
        linkedHashSet.add("Bird");
        System.out.println("LinkedHashSet: " + linkedHashSet);
    }
}
```

Here are the result after add a value to each method,

                          HashSet: [Bird, Cat, Dog]
                          TreeSet: [Bird, Cat, Dog]
                          LinkedHashSet: [Cat, Dog, Bird]

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

Here is the result of code above,

                            Enter the index : 3
                            Element at index 3: JAVA LION

## Remove lines which is duplicated data by 1 key field
