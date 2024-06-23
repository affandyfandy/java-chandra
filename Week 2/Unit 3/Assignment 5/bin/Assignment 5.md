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

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment5C {
    public static void main(String[] args) {
        // Replace these with your actual input and output file paths
        String inputFile = "input.csv";
        String outputFile = "output.csv";

        // Replace this with the column name for the key field in the CSV
        String keyField = "ID"; // Example: "ID" is the key field

        try {
            // Map to store occurrences of each key
            Map<String, Integer> keyCountMap = new HashMap<>();

            // Buffered reader for reading input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Buffered writer for writing output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Read the header line (assuming the first line is header)
            String header = reader.readLine();
            writer.write(header + "\n"); // Write header to output file


```

- File Paths and Key Field: Define the paths for input and output files, and specify the key field (e.g., "ID").
- Setup Readers and Writers: Initialize BufferedReader to read the input file and BufferedWriter to write to the output file.
- Read Header: Read the header line from the input file and write it to the output file.

<br>

```java
            // Read each line from input file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line by comma (CSV assumption)
                String[] parts = line.split(",");

                // Extract key value based on keyField index (adjust accordingly)
                int keyFieldIndex = -1;
                for (int i = 0; i < parts.length; i++) {
                    if (header.split(",")[i].trim().equalsIgnoreCase(keyField.trim())) {
                        keyFieldIndex = i;
                        break;
                    }
                }

                if (keyFieldIndex != -1) {
                    String keyValue = parts[keyFieldIndex].trim();

                    // Update count of key occurrences
                    keyCountMap.put(keyValue, keyCountMap.getOrDefault(keyValue, 0) + 1);
                }
            }

            // Close resources
            reader.close();

```

- First Pass - Count Occurrences:
  - Read each line from the input file.
  - Split the line into parts using commas (assuming CSV format).
  - Find the index of the key field in the header.
  - Extract the key value from the line and update its count in keyCountMap.

<br>

```java
            // Reopen the reader to start from the beginning
            reader = new BufferedReader(new FileReader(inputFile));
            // Skip the header line
            reader.readLine();

            // Read each line from input file again to write non-duplicate lines
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String keyValue = parts[getIndex(header, keyField)].trim();

                // Check if the key value occurs only once
                if (keyCountMap.get(keyValue) == 1) {
                    writer.write(line + "\n");
                }
            }

            // Close resources
            reader.close();
            writer.close();

            System.out.println("Filtered output written to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the index of a field in the CSV header
    private static int getIndex(String header, String field) {
        String[] headers = header.split(",");
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equalsIgnoreCase(field.trim())) {
                return i;
            }
        }
        return -1;
    }
}

```

- Reopen Reader: After counting occurrences, reopen the reader to read the file again from the beginning.
- Skip Header: Skip the header line since it is already written to the output file.
- Second Pass - Write Non-Duplicate Lines:
  - Read each line again.
  - Extract the key value.
  - Write the line to the output file only if the key value occurs exactly once in the input file.
- Close Resources: Close the reader and writer, and print a message indicating the output file is written.

## Shallow copy of a HashMap instance

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
```

Code above, we define a simple Person class with a name field and a constructor to initialize it. The toString method is overridden to return the name of the person, which will be useful for printing.

<br>

```java
public class Assignment5D {
    public static void main(String[] args) {
        // Create a hashmap
        HashMap<String, Person> originalMap = new HashMap<>();
        originalMap.put("003", new Person("Robin Hood"));
        originalMap.put("002", new Person("Ada Wong"));
        originalMap.put("001", new Person("Charlie Casper"));

        // Create a shallow copy from hashmap
        @SuppressWarnings("unchecked")
        HashMap<String, Person> shallowCopyMap = (HashMap<String, Person>) originalMap.clone();

        // Print the output from original and copy
        System.out.println("Original HashMap: " + originalMap);
        System.out.println("Shallow Copy HashMap: " + shallowCopyMap);

        // Modify original
        originalMap.get("001").name = "Alicia";

        // Output
        System.out.println("After modifying an object in the original HashMap:");
        System.out.println("Original HashMap: " + originalMap);
        System.out.println("Shallow Copy HashMap: " + shallowCopyMap);
    }
}

```

- Creating a HashMap:

  ```java
  HashMap<String, Person> originalMap = new HashMap<>();
  originalMap.put("003", new Person("Robin Hood"));
  originalMap.put("002", new Person("Ada Wong"));
  originalMap.put("001", new Person("Charlie Casper"));
  ```

  We create an originalMap and populate it with three entries, each having a key (String) and a value (Person object).

- Creating a Shallow Copy:

  ```java
  @SuppressWarnings("unchecked")
  HashMap<String, Person> shallowCopyMap = (HashMap<String, Person>) originalMap.clone();
  ```

  We create a shallow copy of originalMap using the clone method. The @SuppressWarnings("unchecked") annotation is used to suppress the unchecked cast warning.

- Printing Original and Shallow Copy:

  ```java
  System.out.println("Original HashMap: " + originalMap);
  System.out.println("Shallow Copy HashMap: " + shallowCopyMap);
  ```

  We print both the original and the shallow copy to show their initial states.

- Modifying the Original Map:

  ```java
  originalMap.get("001").name = "Alicia";
  ```

  We modify the name field of the Person object associated with key "001" in the originalMap.

- Printing After Modification:

  ```java
  System.out.println("After modifying an object in the original HashMap:");
  System.out.println("Original HashMap: " + originalMap);
  System.out.println("Shallow Copy HashMap: " + shallowCopyMap);
  ```

  We print both the original and the shallow copy again to observe the effects of the modification.

### Keypoints

- Shallow Copy vs. Deep Copy:

  - Shallow Copy: When you create a shallow copy of a collection (like HashMap), it copies the structure of the original collection (keys and values) but not the objects referenced by the values. In this case, both the original and the copied map refer to the same Person objects.
  - Deep Copy: A deep copy would involve copying the collection and also cloning each of the objects it contains, so the original and the copy do not share any objects.

<br>

## Convert List to Map

```java
class Employee {
    private int employeeID;
    private String name;

    public Employee(int employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(employeeID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                '}';
    }
}
```

- Fields
  - employeeID: Unique identifier for each employee.
  - name: Name of the employee.

<br>

- Constructor:
  - Initializes the employeeID and name fields.

<br>

- Getters:
  - getEmployeeID(): Returns the employee's ID.
  - getName(): Returns the employee's name.

<br>

- hashCode() Method:
  - Overrides hashCode() to generate a hash code based on employeeID.

<br>

- toString() Method:

  - Overrides toString() to provide a string representation of an Employee object.

<br>

```java
List<Employee> employees = new ArrayList<>();
employees.add(new Employee(2024001, "Ryan"));
employees.add(new Employee(2024002, "Chandra"));
employees.add(new Employee(2024003, "Hadi"));
```

- Creates a list called employees and adds three Employee objects to it.

<br>

```java
Map<Integer, Employee> employeeMap = employees.stream()
        .collect(Collectors.toMap(Employee::getEmployeeID, emp -> emp));
```

- Uses Java Streams to convert the list of employees into a map (employeeMap) where the keys are employeeID and the values are Employee objects.
- Employee::getEmployeeID is a method reference used to get the key (employeeID).
- emp -> emp is a lambda expression used to get the value (Employee object).

<br>

```java
Map<Integer, Employee> sortedEmployeeMap = new LinkedHashMap<>();
employeeMap.entrySet().stream()
        .sorted(Map.Entry.comparingByKey()) // Sort by key ascending
        .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));
```

- Creates a LinkedHashMap called sortedEmployeeMap to maintain the order of insertion.
- Converts the employeeMap entry set to a stream and sorts it by keys in ascending order using sorted(Map.Entry.comparingByKey()).
- Uses forEachOrdered to preserve the order of the sorted stream and populates sortedEmployeeMap.

<br>

```java
sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
```

- Iterates over the sorted map and prints each entry in the format key: value.

<br>

## CopyOnWriteArrayList

```java
public class Assignment5F {
    public static void main(String[] args) {
        // Create a CopyOnWriteArrayList and add three elements
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Robin Hood");
        list.add("Ada Wong");
        list.add("Charlie Casper");
```

- A CopyOnWriteArrayList named list is created and initialized with three elements: "Robin Hood", "Ada Wong", and "Charlie Casper".

<br>

```java
        // Start to iterate CopyOnWriteArrayList
        for (String s : list) {
            System.out.println("Iterating: " + s);

            // Modify CopyOnWriteArrayList while iterating
            if (s.equals("Ada Wong")) {
                list.set(1, "Ada Wong Great");
                list.add("four");
                System.out.println("List modified inside iteration");
            }
        }
```

- The code iterates over the list using a for-each loop.
- For each element, it prints "Iterating: " followed by the element.
- When the loop encounters the element "Ada Wong":
  - It modifies the element at index 1 to "Ada Wong Great" using the set method.
  - It adds a new element "four" to the list using the add method.
  - It prints "List modified inside iteration".

<br>

```java
        System.out.println("Final list: " + list);
    }
}
```

- After the iteration, the final state of the list is printed.

### Key Consepts

- CopyOnWriteArrayList Behavior:
  - CopyOnWriteArrayList creates a new copy of the underlying array every time a modification is made (add, set, remove, etc.).
  - Iterators for CopyOnWriteArrayList operate on a snapshot of the array at the time the iterator was created, meaning they are not affected by subsequent modifications to the list.

<br>

- Safe Concurrent Modifications:
  - The main advantage of CopyOnWriteArrayList is that it allows for safe concurrent iteration and modification without throwing ConcurrentModificationException.
  - In this example, even though the list is modified while iterating, it does not affect the iteration process because the iterator is working on a separate snapshot of the array.

#### Output

```java
Iterating: Robin Hood
Iterating: Ada Wong
List modified inside iteration
Iterating: Charlie Casper
Final list: [Robin Hood, Ada Wong Great, Charlie Casper, four]
```

<br>

## ConcurrencyHashMap

```java
public class Assignment5G {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
```

- A ConcurrentHashMap named map is created. This map will be shared between multiple threads for concurrent access and modification.

<br>

```java
        // Thread 1
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                map.put("key" + i, "value" + i);
                System.out.println("Thread 1 added key" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
```

- A new thread is started to add elements to the map.
- In a loop that runs 5 times, the thread adds a key-value pair to the map (key0 -> value0, key1 -> value1, ..., key4 -> value4) and prints a message indicating the addition.
- The thread sleeps for 100 milliseconds between each addition to simulate some delay.

<br>

```java
        // Thread 2
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String value = map.get("key" + i);
                System.out.println("Thread 2 read key" + i + " -> " + value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
```

- Another thread is started to read elements from the map.
- In a loop that runs 5 times, the thread retrieves the value associated with each key (key0, key1, ..., key4) and prints a message indicating the key-value pair read from the map.
- The thread also sleeps for 100 milliseconds between each read operation.

<br>

### Key Consepts

- ConcurrentHashMap:
  - ConcurrentHashMap is a thread-safe implementation of HashMap that allows safe concurrent access and modification by multiple threads.
  - It uses internal locking mechanisms (segmenting the map into different parts) to ensure that read and write operations can occur concurrently without blocking each other.

<br>

- Thread Safety:
  - By using ConcurrentHashMap, the code ensures that both threads can safely access and modify the map concurrently.
  - There is no need for explicit synchronization (e.g., using synchronized blocks) because ConcurrentHashMap handles concurrency internally.

<br>

- Threads:
  - Two threads are created and started using anonymous inner classes implementing the Runnable interface.
  - Thread 1 performs write operations (adding key-value pairs) to the map.
  - Thread 2 performs read operations (retrieving key-value pairs) from the map.

<br>

#### Output

```java
Thread 1 added key0
Thread 2 read key0 -> value0
Thread 1 added key1
Thread 2 read key1 -> value1
Thread 1 added key2
Thread 2 read key2 -> value2
Thread 1 added key3
Thread 2 read key3 -> value3
Thread 1 added key4
Thread 2 read key4 -> value4
```

- The exact order may vary because thread scheduling is handled by the JVM and underlying operating system.
- The important point is that ConcurrentHashMap ensures that all the operations (put and get) are thread-safe, and there are no data consistency issues or exceptions such as ConcurrentModificationException.

<br>

## equal() and hashcode()

```java
class Persons {
    private String name;
    private int age;

    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Persons Persons = (Persons) obj;
        return age == Persons.age && Objects.equals(name, Persons.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Persons{name='" + name + "', age=" + age + '}';
    }
}
```

- Fields: - name (String): Name of the person. - age (int): Age of the person.
  <br>

- Constructor:
  - Initializes name and age fields.

<br>

- equals Method:
  - Overrides Object.equals to provide a custom equality check.
  - Two Persons objects are considered equal if they have the same name and age.
  - Uses Objects.equals to compare name and directly compares age.

<br>

- hashCode Method:
  - Overrides Object.hashCode to provide a consistent hash code for Persons objects.
  - Uses Objects.hash to generate a hash code based on name and age.

<br>

- toString Method:
  - Provides a string representation of the Persons object.

<br>

```java
public class Assignment5H {
    public static void main(String[] args) {
        Set<Persons> people = new HashSet<>();
        people.add(new Persons("Alice", 30));
        people.add(new Persons("Bob", 25));
        people.add(new Persons("Alice", 30));

        // The set should contain only two unique Personss because Alice with age 30 is added twice
        System.out.println("Number of unique Personss: " + people.size()); // Output: 2

        // Print the unique Personss in the set
        for (Persons Persons : people) {
            System.out.println(Persons);
        }
    }
}
```

- Creating a HashSet:
  - A HashSet named people is created to store Persons objects.

<br>

- Adding Elements to the Set:
  - Three Persons objects are added to the set:
    - new Persons("Alice", 30)
    - new Persons("Bob", 25)
    - new Persons("Alice", 30) (duplicate)

<br>

- Checking Unique Elements:
  - The set should contain only two unique Persons objects because HashSet does not allow duplicates.
  - The duplicates are identified based on the equals and hashCode methods.

<br>

- Printing the Size of the Set:
  - The size of the set is printed, which should be 2.

<br>

- Printing the Unique Persons Objects:
  - Each unique Persons object in the set is printed.

### Key Consepts

- HashSet Behavior:
  - HashSet relies on the equals and hashCode methods to determine the uniqueness of its elements.
  - When an element is added to a HashSet, it checks if the element already exists in the set by using these methods.
  - If the element already exists (i.e., an equal element is found), it is not added again.

<br>

- equals and hashCode Methods:
  - The equals method ensures that two Persons objects with the same name and age are considered equal.
  - The hashCode method ensures that equal Persons objects generate the same hash code.

#### Output

```java
Number of unique Personss: 2
Persons{name='Alice', age=30}
Persons{name='Bob', age=25}
```

## Add employee to HashSet

```java
public class Assignment5I {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2024001, "Ryan"));
        employees.add(new Employee(2024002, "Chandra"));
        employees.add(new Employee(2024003, "Hadi"));
        employees.add(new Employee(2024002, "Duplicate Chandra"));
    }
}
```

- A list of Employee objects is created and populated with four employees, including a duplicate entry for the employee ID 2024002.

<br>

```java
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getEmployeeID, emp -> emp, (existing, replacement) -> existing));
```

- The list of employees is converted to a map where the key is the employeeID and the value is the Employee object.
- The toMap collector uses a merge function (existing, replacement) -> existing to handle duplicate keys by keeping the existing entry and ignoring the replacement.

<br>

```java
        Map<Integer, Employee> sortedEmployeeMap = new LinkedHashMap<>();
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by key ascending
                .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));
```

- The employeeMap is sorted by keys in ascending order.
- A LinkedHashMap is used to maintain the insertion order of entries as per the sorted keys.

<br>

```java
        sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));

        Set<Employee> employeeSet = new HashSet<>(employees);
        System.out.println("\nHashSet contents:");
        employeeSet.forEach(System.out::println);
```

- The sorted map is printed to the console, displaying each employee's ID and details.
- The list of employees is added to a HashSet.
- The HashSet will eliminate duplicate entries based on the equals and hashCode methods of the Employee class.
- The contents of the HashSet are printed, showing unique employees.

<br>

### Key Consepts

- Handling Duplicates in Map:
  - When converting the list to a map, duplicates are handled using a merge function that keeps the existing value and ignores the new one if a duplicate key is encountered.

<br>

- Sorting the Map:
  - The map entries are sorted by keys using the sorted method and comparingByKey comparator.
  - A LinkedHashMap is used to store the sorted entries to maintain the order of insertion.

<br>

- Eliminating Duplicates in Set:
  - The HashSet automatically handles duplicates by relying on the equals and hashCode methods of the Employee class.
  - If two Employee objects have the same employeeID and name, they are considered equal, and only one instance will be kept in the HashSet.

<br>

#### Output

```java
2024001: Employee{employeeID=2024001, name='Ryan'}
2024002: Employee{employeeID=2024002, name='Chandra'}
2024003: Employee{employeeID=2024003, name='Hadi'}

HashSet contents:
Employee{employeeID=2024001, name='Ryan'}
Employee{employeeID=2024002, name='Chandra'}
Employee{employeeID=2024003, name='Hadi'}
Employee{employeeID=2024002, name='Duplicate Chandra'}
```

## Composite Key

```java
class Employee5J {
    private int employeeID;
    private String name;
    private String department;

    public Employee5J(int employeeID, String name, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
```

- This class represents an employee with employeeID, name, and department attributes.
- It includes getter methods for each attribute and a toString method to print the employee's information.

<br>

```java
class CompositeKey {
    private String department;
    private int employeeID;

    public CompositeKey(String department, int employeeID) {
        this.department = department;
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return department;
    }

    public int getEmployee() {
        return employeeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CompositeKey that = (CompositeKey) obj;
        return employeeID == that.employeeID && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employeeID);
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "department='" + department + '\'' +
                ", employeeID=" + employeeID +
                '}';
    }
}
```

- This class represents a composite key with department and employeeID attributes.
- It overrides equals and hashCode methods to ensure proper comparison and hashing based on both department and employeeID.
- It also has a toString method to print the composite key.

<br>

```java
public class Assignment5J {
    public static void main(String[] args) {
        // Step 1: Define Employee class

        // Step 2: Create a List of Employees
        List<Employee5J> employees = new ArrayList<>();
        employees.add(new Employee5J(2024001, "Ryan", "HR"));
        employees.add(new Employee5J(2024002, "Chandra", "IT"));
        employees.add(new Employee5J(2024003, "Hadi", "Finance"));
        employees.add(new Employee5J(2024002, "Duplicate Chandra", "IT")); // Duplicate in the same department
        Map<CompositeKey, Employee5J> employeeMap = employees.stream()
            .collect(Collectors.toMap(
                emp -> new CompositeKey(emp.getDepartment(),
                emp getEmployeeID()),
                emp -> emp,
                (existing, replacement) -> existing));
```

- This is the main class and method where the program execution begins.
- A list of Employee5J objects is created and populated with four employees, including a duplicate entry for the employee ID 2024002 in the same department IT.
- The list of employees is converted to a map where the key is a CompositeKey object containing the department and employeeID, and the value is the Employee5J object.
- The toMap collector uses a merge function (existing, replacement) -> existing to handle duplicate keys by keeping the existing entry and ignoring the replacement.

<br>

```java
        Map<CompositeKey, Employee5J> sortedEmployeeMap = new LinkedHashMap<>();
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((key1, key2) -> {
                    int deptCompare = key1.getDepartment().compareTo(key2.getDepartment());
                    if (deptCompare == 0) {
                        return Integer.compare(key1.getEmployee(), key2.getEmployee());
                    } else {
                        return deptCompare;
                    }
                })) // Sort by department then by employeeID
                .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));
        sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
```

- The employeeMap is sorted by keys using the sorted method with a custom comparator that first compares departments and then compares employee IDs if departments are equal.
- A LinkedHashMap is used to maintain the insertion order of entries as per the sorted keys.
- The sorted map is printed to the console, displaying each composite key and the corresponding employee's details.

<br>

#### Output

```java
CompositeKey{department='Finance', employeeID=2024003}: Employee{employeeID=2024003, name='Hadi', department='Finance'}
CompositeKey{department='HR', employeeID=2024001}: Employee{employeeID=2024001, name='Ryan', department='HR'}
CompositeKey{department='IT', employeeID=2024002}: Employee{employeeID=2024002, name='Chandra', department='IT'}
```

- Employee Class: Represents an employee with employeeID, name, and department.
- CompositeKey Class: Represents a composite key with department and employeeID, with proper equals and hashCode methods for correct comparison and hashing.
- Map Creation: The list of employees is converted to a map with CompositeKey as the key, handling duplicates by keeping the existing entry.
- Sorting: The map is sorted by department and then by employee ID using a custom comparator and stored in a LinkedHashMap.
- Output: The sorted map is printed, showing employees sorted first by department and then by employee ID.

## Problem by Using For Each

```java
import java.util.ArrayList;
import java.util.List;

public class Assignment5K {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        data.add("Joe");
        data.add("Hellen");
        data.add("Test");
        data.add("Rian");
        data.add("Ruby");

        for (String d : data) {
            if (d.equals("Test")) {
                data.remove(d);
            }
        }
    }
}
```

This Code will produce error like below,

```java
Exception in thread "main" java.util.ConcurrentModificationException
        at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
        at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
        at Assignment5K.main(Assignment5K.java:14)
```

Code Above is error java.util.ConcurrentModificationException occurs because you are trying to modify the List while iterating over it using a for-each loop. This is not allowed because the underlying iterator of the for-each loop detects that the list is being modified during iteration.

<br>

### Explanation

In code above, when the loop encounters the string "Test" and tries to remove it from the list, it results in a ConcurrentModificationException because the ArrayList's iterator detects a structural modification to the list while it is being iterated. So the solution is using a _Regular For Loop_: Iterate over the list using a traditional for loop and manually manage the index.

here is the modification for the code above

```java
public class Assignment5K {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        data.add("Joe");
        data.add("Hellen");
        data.add("Test");
        data.add("Rian");
        data.add("Ruby");

        System.out.println("Before Removing");
        for (String d : data) {
            System.out.println(d);
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == "Test") {
                data.remove(i);
            }
        }
        System.out.println("\nAfter Removing");
        for (String d : data) {
            System.out.println(d);
        }
    }
}
```

<br>

## ConcurrencyModificationException

When multiple threads access and modify a shared collection concurrently without proper synchronization, several issues can arise, one of which is ConcurrentModificationException. Here’s a detailed explanation of what happens and how to handle it:

- Data Inconsistency:
  - If two or more threads simultaneously modify a shared collection, the collection’s internal state can become inconsistent. For example, one thread might be adding an element while another thread is removing an element, leading to a corrupted state where the internal data structures of the collection are no longer valid.

<br>

- ConcurrentModificationException:
  - In Java, collections such as ArrayList, HashSet, and HashMap are not synchronized by default. When one thread modifies a collection while another thread is iterating over it, the iterator detects that the collection has been structurally modified and throws ConcurrentModificationException to indicate that concurrent modification has occurred.

<br>

- Race Conditions:
  - A race condition occurs when the outcome of the execution depends on the timing or order of thread scheduling. This can lead to unpredictable and incorrect behavior, such as missed updates, duplicated entries, or corrupted data.
