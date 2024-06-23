# Assignment 8

## Serialization

Serialization in Java refers to the process of converting an object into a stream of bytes so that it can be easily saved to a file or sent over a network. This allows objects to be saved and restored at a later time, preserving their state.

### Function Serialization

- Persistence: Serialization allows objects to be saved to a file and later restored, enabling persistence of object state.
- Network Communication: Serialized objects can be easily sent across a network between applications, facilitating communication between distributed systems.
- Deep Copy: Serialization can be used to create deep copies of objects by serializing them and then deserializing the resulting byte stream into a new object.
- Caching: Serialized objects can be stored in a cache for efficient retrieval, reducing the need to recreate objects from scratch.

<br>

### How Serialization Works in Java:

- To serialize an object in Java, the class must implement the Serializable interface. This is a marker interface (does not have any methods) that indicates that the objects of the class can be serialized.
- Serialization is performed using ObjectOutputStream to write objects to an output stream (such as a file or network socket).
- Deserialization (the process of reconstructing the object from its serialized form) is done using ObjectInputStream.

## serialVersionUID

The serialVersionUID in Java is a unique identifier for serializable classes. It is a static final long variable that is used during the serialization and deserialization process to ensure that the serialized and deserialized objects have the same version.

### Purpose of serialVersionUID:

- Versioning Control: When you serialize an object, the serialVersionUID is also serialized with it. When you deserialize an object, Java uses this identifier to ensure that the class definition at the time of serialization matches the class definition at the time of deserialization. This prevents class version mismatches that could lead to InvalidClassException.
- Compatibility: If the serialVersionUID of the serialized object matches the serialVersionUID of the class in the current JVM, Java assumes the classes are compatible, and deserialization proceeds. If they do not match, an exception is thrown, indicating a version mismatch.
- Explicit Versioning: By explicitly declaring a serialVersionUID, developers can control the versioning of their classes. This is particularly useful when you want to manage how changes in class definitions (such as adding or removing fields) affect serialization compatibility.

<br>

### Declaration and Usage:

- The serialVersionUID is usually declared in the class implementing Serializable as follows:
  ```java
  private static final long serialVersionUID = 123456789L;
  ```
- It's recommended to explicitly declare serialVersionUID to avoid automatic generation by the Java compiler, which may change unexpectedly based on the class definition.
- If serialVersionUID is not explicitly declared, the Java runtime system automatically generates one based on various aspects of the class, which might not be consistent across different compilers, versions of Java, or changes in the class structure.

<br>

Example for using serialVersion UID in java,

```java
import java.io.Serializable;

// A serializable class with serialVersionUID explicitly defined
class MyClass implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private String name;
    private int age;

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        // Serialization
        try {
            MyClass obj = new MyClass("John Doe", 30);
            // Serialization code here
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserialization
        try {
            MyClass obj;
            // Deserialization code here
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

In this example, MyClass declares a serialVersionUID (123456789L). This ensures that as long as the serialVersionUID remains unchanged, serialized objects can be deserialized successfully across different versions of the class as long as they are compatible.

## Illustrate Serialization and Deserialization Using serialVersionUID

```java
import java.io.Serializable;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Define serialVersionUID

    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Override toString for printing
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

```

- Define the Employee Class:
  - Ensure Employee implements Serializable.
  - Define a serialVersionUID for versioning control.

<br>

```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationExample {
    public static void main(String[] args) {
        // Create a list of Employee objects
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", 50000));
        employees.add(new Employee(2, "Jane Smith", 60000));
        employees.add(new Employee(3, "Mike Johnson", 55000));

        // Serialization
        try {
            FileOutputStream fileOut = new FileOutputStream("employees.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employees);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in employees.ser");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
```

- Serialization: Write List of Employees to File:
  - Create a list of Employee objects.
  - Serialize the list and write it to a file (employees.ser).

<br>

```java
import java.io.*;
import java.util.List;

public class DeserializationExample {
    public static void main(String[] args) {
        List<Employee> employees = null;

        // Deserialization
        try {
            FileInputStream fileIn = new FileInputStream("employees.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employees = (List<Employee>) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Print deserialized employees
        if (employees != null) {
            System.out.println("Deserialized Employees:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}

```

- Deserialization: Read File to Convert to Object:
  - Read the file (employees.ser).
  - Deserialize the contents back into a list of Employee objects.

### Points

- serialVersionUID: In the Employee class, define serialVersionUID to ensure versioning control. If the class definition changes in a way that affects serialization compatibility, you can increment this version to manage deserialization properly.

- Exception Handling: Both serialization and deserialization can throw IOException and ClassNotFoundException. Proper exception handling is crucial to manage file I/O errors and class loading issues during deserialization.

- Object Casting: After deserialization, cast the returned object to the appropriate type (List<Employee> in this case) to work with the deserialized data.

#### Output

```java
Serialized data is saved in employees.ser
Deserialized Employees:
Employee{id=1, name='Ada Wong', salary=5000000.0}
Employee{id=2, name='Zaria Onths', salary=6800000.0}
Employee{id=3, name='Simalakama', salary=4950000.0}
```
