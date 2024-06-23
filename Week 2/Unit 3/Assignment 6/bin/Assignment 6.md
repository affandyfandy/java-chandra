# Assignment 6

## Parallel Stream

When to use parallel streams:

- Large Data Sets: Parallel streams are most effective with large collections.
- Independent Tasks: When tasks can be executed independently without requiring synchronization.
- CPU-bound Tasks: Tasks that are intensive in terms of CPU usage benefit more from parallel streams.

<br>

here is the demo of using parallel stream

```java
import java.util.Arrays;
import java.util.List;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Summing using sequential stream
        int sumSequential = numbers.stream()
                                   .mapToInt(Integer::intValue)
                                   .sum();
        System.out.println("Sum using sequential stream: " + sumSequential);

        // Summing using parallel stream
        int sumParallel = numbers.parallelStream()
                                 .mapToInt(Integer::intValue)
                                 .sum();
        System.out.println("Sum using parallel stream: " + sumParallel);
    }
}
```

### Explanation

- Sequential Stream:
  - numbers.stream(): Creates a sequential stream.
  - mapToInt(Integer::intValue): Maps each Integer to an int.
  - sum(): Sums the int values.

<br>

- Parallel Stream:
  - numbers.parallelStream(): Creates a parallel stream.
  - mapToInt(Integer::intValue): Maps each Integer to an int.
  - sum(): Sums the int values using multiple threads.

## Remove Duplicate Using Stream

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment6B {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Chandra", "Ryan", "Chandra", "Hadi", "Ryan", "Rimba");

        // Remove duplicates using stream
        List<String> distinctStrings = strings.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("List with duplicates removed: " + distinctStrings);
    }
}
```

### Explanation

- Input List: The input list strings contains some duplicate elements.
- Stream Creation: strings.stream() creates a sequential stream from the list.
- Distinct Elements: .distinct() returns a stream consisting of the distinct elements.
- Collect to List: collect(Collectors.toList()) collects the distinct elements into a new list.
- Output: The resulting list distinctStrings contains only the unique elements from the original list.

The result of code above will be like below,

```java
List with duplicates removed: [Chandra, Ryan, Hadi, Rimba]
```

<br>

## Remove Lines Which is Duplicated from CSV

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Assignment6C {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.csv"; // Replace with your input CSV file path
        String outputFile = "output.csv"; // Replace with your output CSV file path
        String keyFieldName = "id"; // Replace with the name of the key field in the CSV

        // HashSet to store seen keys
        Set<String> seenKeys = new HashSet<>();

        // Open input file and output writer
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            // Read header line
            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            // Find key field index in header
            String[] headers = header.split(",");
            int keyIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase(keyFieldName)) {
                    keyIndex = i;
                    break;
                }
            }
            if (keyIndex == -1) {
                throw new IllegalArgumentException("Key field not found in CSV header: " + keyFieldName);
            }

            // Process each line in the CSV
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1); // Split line into fields

                // Extract key from the line based on keyIndex
                String key = fields[keyIndex].trim();

                // Write to output file if key is not seen before
                if (!seenKeys.contains(key)) {
                    writer.write(line);
                    writer.newLine();
                    seenKeys.add(key);
                }
            }
        }
    }
}
```

### Explanation

- Input and Output Files: Specify the inputFile and outputFile variables with the paths to your input and desired output files.
- Key Position: For .txt files, specify the keyPosition variable (0-based index) where the key field is located.
- Reading and Writing Files:
  - Use Files.lines() to read lines from the input file as a stream.
  - Use a HashSet (seenKeys) to track keys that have already been encountered to filter duplicates.
  - For each line, extract the key field based on whether the file is .csv or .txt.
  - Write each unique line to the output file using Files.write() ensuring each line is appended to the file (StandardOpenOption.APPEND).

<br>

## Count the Number of Strings in a list start with specific letter using stream

```java
import java.util.Arrays;
import java.util.List;

public class Assignment6D {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
        String startingLetter = "G";

        long count = strings.stream()
                .filter(str -> str.startsWith(startingLetter))
                .count();

        System.out.println("Number of strings starting with \"" + startingLetter + "\": " + count);
    }
}
```

### Explanation

- Input List: strings is the list of strings provided: ["Red", "Green", "Blue", "Pink", "Brown"].
- Starting Letter: startingLetter is set to "G".
- Stream Pipeline:
  - strings.stream() creates a stream from the list.
  - .filter(str -> str.startsWith(startingLetter)) filters the stream to include only strings that start with "G".
  - .count() calculates the number of elements in the filtered stream.
- Output: Prints the count of strings that start with "G".

<br>

## Input: list of employees

```java
class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    // Constructor
    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    // toString method to print Employee details
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
```

Code above is to create an employee model that gives ID, Name, Age, and Salary.

### Sort name in alphabetical, ascending using streams

```java
public class Assignment6E {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));

        // Sorting names alphabetically in ascending order
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee -> System.out.println(employee.getName()));
    }
}
```

- Sorting Names: Use sorted() with a comparator (Comparator.comparing(Employee::getName)) to sort employees by name alphabetically in ascending order.

#### Output

```java
Ada Wong
Rinda Lesmana
Simalakama
Zaria Onths
```

<br>

### Find employee has max salary using streams

```java
public class Assignment6E {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));

        // Finding employee with max salary
        Optional<Employee> maxSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        maxSalaryEmployee
                .ifPresent(employee -> System.out.println("\nEmployee with max salary: " + employee.getName()));
    }
}
```

- Finding Max Salary: Use max() with a comparator (Comparator.comparingInt(Employee::getSalary)) to find the employee with the maximum salary. Use Optional<Employee> to handle the possibility of the list being empty.

#### Output

```java
Employee with max salary: Rinda Lesmana
```

<br>

### Check any employee names match with specific keywords or not

```java
public class Assignment6E {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));

        // Specific keywords to check
        String[] keywords = { "Rinda" };

        // Check if any employee names match specific keywords
        boolean matchFound = employees.stream()
                .anyMatch(employee -> Arrays.stream(keywords)
                        .anyMatch(keyword -> employee.getName().contains(keyword)));

        System.out.println("\nMatch found: " + matchFound);
    }
}
```

- Checking Names: Use anyMatch() to check if any employee name contains any of the specified keywords (keywords). Nested streams are used to iterate over both employees and keywords efficiently.

#### Output

```java
Match found: true
```

<br>

## Convert list employees to map with ID as key

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment6F {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));
        // Convert list of employees to map with ID as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, employee -> employee));

        // Print the map
        employeeMap.forEach((id, employee) -> System.out.println("ID: " + id + ", Employee: " + employee));
    }
}
```

### Explanation

- Collectors.toMap(): Converts the stream of Employee objects into a Map<Integer, Employee> where Employee::getId is used as the key and employee -> employee is used as the value.
- Lambda Expressions:
  - Employee::getId extracts the ID of each Employee object to use as the key in the map.
  - employee -> employee maps each Employee object directly to itself as the value in the map.
- Printing the Map: Uses forEach() to iterate over the entries of the Map and print each ID along with its corresponding Employee object.

#### Output

```java
ID: 1, Employee: Employee{id=1, name='Ada Wong', age=30, salary=5000000.0}
ID: 2, Employee: Employee{id=2, name='Zaria Onths', age=25, salary=6800000.0}
ID: 3, Employee: Employee{id=3, name='Simalakama', age=35, salary=4950000.0}
ID: 4, Employee: Employee{id=4, name='Rinda Lesmana', age=28, salary=1.1E7}
```
