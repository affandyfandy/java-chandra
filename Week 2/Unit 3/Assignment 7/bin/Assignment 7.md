# Assignment 7

## Remove duplicated items for any object and any duplicated fields

```java
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Assignment7A {
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
```

Define a Person Class inlude name and age.

<br>

```java
@SafeVarargs
public static <T> List<T> distinctByKeys(List<T> list, Function<? super T, ?>... keyExtractors)
```

- This method is a generic method where T represents the type of objects in the list.
- Function<? super T, ?>... keyExtractors allows passing one or more functions that extract keys from objects of type T. These keys are used to determine uniqueness.

<br>

```java
Set<List<?>> seen = new HashSet<>();
```

- A Set is used to store lists of keys that have already been encountered. This helps in filtering out duplicates.

<br>

```java
return list.stream()
           .filter(t -> {
               List<?> keys = Arrays.stream(keyExtractors)
                                    .map(ke -> ke.apply(t))
                                    .collect(Collectors.toList());
               return seen.add(keys);
           })
           .collect(Collectors.toList());

```

- Convert the list to a stream to process each element.
- Use the filter method to keep only unique elements based on the keys extracted.
- For each element t, generate a list of keys by applying each key extractor function (in this case, just Person::getName) to t.
- Add the list of keys to the seen set. If the set didn't already contain this list of keys, the element is kept; otherwise, it's filtered out.
- Collect the filtered elements into a new list and return it.

<br>

```java
List<Person> persons = new ArrayList<>();
persons.add(new Person("Alice", 30));
persons.add(new Person("Bob", 25));
persons.add(new Person("Alice", 30));  // Duplicate by name
persons.add(new Person("Charlie", 35));
persons.add(new Person("Bob", 25));    // Duplicate by name
persons.add(new Person("David", 28));
persons.add(new Person("Eve", 32));
```

- Create a List of Person Objects

<br>

```java
List<Person> distinctByName = distinctByKeys(persons, Person::getName);
System.out.println("Distinct by name:");
distinctByName.forEach(person -> System.out.println(person.getName() + ", " + person.getAge()));
```

- Initialization:
  - seen is initialized as an empty HashSet.
- Processing Each Person:
  - For each Person object in the persons list, Person::getName is applied to extract the name.
  - The resulting key (the name) is added to the seen set.
  - If the name was not already in the seen set, the person is included in the result list.
  - If the name was already in the seen set, the person is filtered out as a duplicate.

#### Output

```java
Distinct by name:
Alice, 30
Bob, 25
Charlie, 35
David, 28
Eve, 32
```

<br>

## Using Wildcards With Generics

```java
import java.util.ArrayList;
import java.util.List;

public class Assignment7B {

    // Method to print elements of a list with an upper bounded wildcard
    public static void printList(List<? extends Number> list) {
        for (Number n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.1);
        doubles.add(2.2);
        doubles.add(3.3);

        // Print lists using the method with an upper bounded wildcard
        printList(integers);
        printList(doubles);
    }
}
```

### Explanation

- Explanation: The printList method accepts a List parameter with an upper bounded wildcard <? extends Number>. This means it can accept a List of any type that is a subtype of Number (such as Integer, Double, etc.).

- Usage: In main(), we pass List<Integer> and List<Double> to printList, demonstrating that the method can handle both Integer and Double lists because both types are subtypes of Number.

<br>

## Input: list of any object

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class Persons {
    private String name;
    private int age;

    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

Code above is to create a Persons Class which include name and age.

### Sort by any field

```java
public class Assignment7C {
    public static <T> void sortByField(List<T> list, Comparator<? super T> comparator) {
        list.sort(comparator);
    }

    // Method to find the item with maximum value of any field
    public static <T, U extends Comparable<? super U>> T findMaxItem(List<T> list, Function<T, U> fieldExtractor) {
        Optional<T> maxItem = list.stream()
                .max(Comparator.comparing(fieldExtractor));
        return maxItem.orElse(null);
    }

    public static void main(String[] args) {
        List<Persons> persons = new ArrayList<>();
        persons.add(new Persons("Alice", 30));
        persons.add(new Persons("Bob", 25));
        persons.add(new Persons("Charlie", 35));

        // Sort persons by age (ascending)
        sortByField(persons, Comparator.comparingInt(Persons::getAge));

        System.out.println("Sorted by age:");
        for (Persons person : persons) {
            System.out.println(person.getName() + ", " + person.getAge());
        }

        // Sort persons by name (alphabetically)
        sortByField(persons, Comparator.comparing(Persons::getName));

        System.out.println("\nSorted by name:");
        for (Persons person : persons) {
            System.out.println(person.getName() + ", " + person.getAge());
        }
    }
}

```

#### Explanation

- Sorting by Any Field:
  - sortByField method uses a Comparator to sort the list by a specified field. It takes advantage of Java's lambda expressions and method references to provide flexibility in sorting by different fields (Person::getAge for sorting by age, Person::getName for sorting by name).

#### Output

```java
Sorted by age:
Bob, 25
Alice, 30
Charlie, 35

Sorted by name:
Alice, 30
Bob, 25
Charlie, 35
```

<br>

### Find item has max value of any field

```java
public class Assignment7C {
    public static <T> void sortByField(List<T> list, Comparator<? super T> comparator) {
        list.sort(comparator);
    }

    // Method to find the item with maximum value of any field
    public static <T, U extends Comparable<? super U>> T findMaxItem(List<T> list, Function<T, U> fieldExtractor) {
        Optional<T> maxItem = list.stream()
                .max(Comparator.comparing(fieldExtractor));
        return maxItem.orElse(null);
    }

    public static void main(String[] args) {
        List<Persons> persons = new ArrayList<>();
        persons.add(new Persons("Alice", 30));
        persons.add(new Persons("Bob", 25));
        persons.add(new Persons("Charlie", 35));

        // Find person with maximum age
        Persons oldestPerson = findMaxItem(persons, Persons::getAge);
        System.out.println("Person with maximum age: " + oldestPerson.getName() + ", " + oldestPerson.getAge());

        // Find person with maximum name (lexicographically)
        Persons personWithMaxName = findMaxItem(persons, Persons::getName);
        System.out.println(
                "Person with maximum name: " + personWithMaxName.getName() + ", " + personWithMaxName.getAge());
    }
}

```

#### Explanation

- Finding Item with Maximum Value of Any Field:
  - findMaxItem method uses Java Streams to find the item with the maximum value of any field. It accepts a Function (fieldExtractor) that extracts the field value from each item (Person in this case) and uses Comparator.comparing to compare those values. It returns an Optional containing the item with the maximum value, or null if the list is empty.

#### Output

```java
Person with maximum age: Charlie, 35
Person with maximum name: Charlie, 35
```

<br>

## Convert list any object to map with any key field

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Assignment7D {

    public static <T, K> Map<K, T> listToMap(List<T> list, Function<T, K> keyExtractor) {
        return list.stream()
                .collect(Collectors.toMap(keyExtractor, Function.identity()));
    }

    public static void main(String[] args) {
        List<Persons> persons = new ArrayList<>();
        persons.add(new Persons("Alice", 30));
        persons.add(new Persons("Bob", 25));
        persons.add(new Persons("Charlie", 35));

        // Convert list of persons to a map with name as key
        Map<String, Persons> nameToPersonMap = listToMap(persons, Persons::getName);

        // Convert list of persons to a map with age as key
        Map<Integer, Persons> ageToPersonMap = listToMap(persons, Persons::getAge);

        // Print the maps
        System.out.println("Map with name as key:");
        nameToPersonMap.forEach(
                (name, person) -> System.out.println(name + " -> " + person.getName() + ", " + person.getAge()));

        System.out.println("\nMap with age as key:");
        ageToPersonMap
                .forEach((age, person) -> System.out.println(age + " -> " + person.getName() + ", " + person.getAge()));
    }
}
```

On code above Persons Object refers to Persons' Class on Assignment 7C

### Explanation

- Generic Method listToMap:

  - The listToMap method is generic and takes two parameters:
    - list: The list of objects (List<T>).
    - keyExtractor: A Function that extracts the key (K) from each object (T).
  - Inside the method, it uses Java streams (list.stream()) to convert the list into a Map<K, T> using Collectors.toMap.
  - keyExtractor is used to extract the key from each object (T), and Function.identity() is used as the value extractor (Function.identity() returns the object itself).

- Usage in main Method:

  - In main, we create a list of Person objects (persons).
  - We then use listToMap to convert persons into two maps:
    - nameToPersonMap: Maps each person's name (String) to the corresponding Person object.
    - ageToPersonMap: Maps each person's age (Integer) to the corresponding Person object.

- Printing Maps:
  - The forEach method is used to iterate over entries of the maps and print them for demonstration purposes.

#### Output

```java
Map with name as key:
Bob -> Bob, 25
Alice -> Alice, 30
Charlie -> Charlie, 35

Map with age as key:
35 -> Charlie, 35
25 -> Bob, 25
30 -> Alice, 30
```

<br>

## Paginations in Java

```java
import java.util.List;

public class Paginator<T> {
    private List<T> data;
    private int pageSize;
    private int totalItems;

    public Paginator(List<T> data, int pageSize) {
        this.data = data;
        this.pageSize = pageSize;
        this.totalItems = data.size();
    }

    public List<T> getPage(int pageNumber) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        return data.subList(startIndex, endIndex);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        this.totalItems = data.size();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}

```

- Fields:
  - data: List of objects (T) to be paginated.
  - pageSize: Number of items per page.
  - totalItems: Total number of items in the data list.
- Constructor:
  - Initializes the Paginator with the provided data list and page size. It calculates the total number of items (totalItems) in the data list.
- Methods:
  - getPage(int pageNumber): Retrieves a sublist (page) of data based on the specified page number. It calculates the start and end indexes of the sublist based on pageNumber and pageSize.
  - getTotalPages(): Calculates and returns the total number of pages needed to display all items based on the current pageSize.
  - getData(), setData(List<T> data): Getter and setter for the data list.
  - getPageSize(), setPageSize(int pageSize): Getter and setter for the page size.
  - getTotalItems(), setTotalItems(int totalItems): Getter and setter for the total number of items.

<br>

Based on that class we can use main like code below,

```java
import java.util.ArrayList;
import java.util.List;

public class PaginatorDemo {
    public static void main(String[] args) {
        // Create a list of objects (e.g., Person objects)
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 30));
        persons.add(new Person("Bob", 25));
        persons.add(new Person("Charlie", 35));
        persons.add(new Person("David", 28));
        persons.add(new Person("Eve", 32));

        // Initialize Paginator with list of persons and page size of 2
        Paginator<Person> paginator = new Paginator<>(persons, 2);

        // Get and print total pages
        int totalPages = paginator.getTotalPages();
        System.out.println("Total pages: " + totalPages);

        // Iterate over each page and print the contents
        for (int page = 1; page <= totalPages; page++) {
            List<Person> pageData = paginator.getPage(page);
            System.out.println("\nPage " + page + ":");
            for (Person person : pageData) {
                System.out.println(person.getName() + ", " + person.getAge());
            }
        }
    }

    // Example class representing a Person with name and age fields
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
```

_Explanation of Demo:_

- Paginator Initialization: Creates a Paginator instance (paginator) with a list of Person objects (persons) and a page size of 2.

- Pagination: It calculates and prints the total number of pages (getTotalPages()). Then, it iterates over each page, retrieves the data for that page using getPage(p age), and prints the name and age of each Person object on that page.

_Notes:_

- This Paginator class provides a generic way to handle pagination for any list of objects in Java.
- Adjust the Person class and the main method according to your specific object structure and pagination requirements.
- You can extend the Paginator class further to include additional features such as sorting, filtering, or handling edge cases like empty lists or invalid page numbers.

#### Output

```java
Total pages: 3

Page 1:
Alice, 30
Bob, 25

Page 2:
Charlie, 35
David, 28

Page 3:
Eve, 32
```
