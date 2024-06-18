# Assignment 2

## Assignment 2A

### What happen if implement 2 interface have same default method? How to solve? Demo in code.

Here is the code example for implementing 2 interface with the same default method.

```java
interface firstInterface {

    default void log(String string) {
        System.out.println("This method is default implementation from first interface " + string);
    }
}

interface secondInterface {
    default void log(String string) {
        System.out.println("This method is default implementation from second interface " + string);
    }

}

public class Assignment2A implements firstInterface, secondInterface {

    public static void main(String[] args) throws Exception {
        Assignment2A assignment = new Assignment2A();

        assignment.log("Interface");
    }

}


```

The code above will show like this,

```
Duplicate default methods named log with the parameters (String) and (String) are inherited from the types firstInterface and secondInterface
```

When a class implements two interfaces that both have the same default method, it creates a conflict because the compiler doesn't know which method to choose. To resolve this conflict, we have to override the method in the class that implements both interfaces and explicitly specify which default method we want to call from each interface.

So in that case we will add a @override notation for implements both method.

```java
interface firstInterface {

    default void log(String string) {
        System.out.println("This method is default implementation from first Interface " + string);
    }
}

interface secondInterface {
    default void log(String string) {
        System.out.println("This method is default implementation from second Interface " + string);
    }

}

public class Assignment2A implements firstInterface, secondInterface {

    @Override
    public void log(String string) {

        firstInterface.super.log(string);
        secondInterface.super.log(string);
    }

    public static void main(String[] args) throws Exception {
        Assignment2A assignment = new Assignment2A();
        assignment.log("Interface");
    }

}


```

And the result of that code will be shown below,

```
This method is default implementation from first Interface Interface
This method is default implementation from second Interface Interface
```

#### Explanation

- Interfaces 'firstInterface' and 'secondInterface'
  - Both interfaces have a default method 'log' with their own implementations.

<br>

- Class 'Assignment2A'
  - Implements both interfaces.
  - Overrides the log method to resolve the conflict by explicitly calling the default method from each interface using firstInterface.super.log(string) and secondInterface.super.log(string).

<br>

- Main Method
  - Creates an instance of Assignment2A.
  - Calls the firstMethod, secondMethod, and log to demonstrate the behavior of the default methods and conflict resolution.

<br>

## Assignment 2B

### Explain the difference between abstract class and interface (Syntax and Purpose)

**Abstract:** An abstract class in Java is a class that cannot be instantiated on its own and is meant to be subclassed. It can contain both abstract methods (methods without a body) and concrete methods (methods with a body).

**Interface:** An interface in Java is a reference type that can contain only constants, method signatures, default methods, static methods, and nested types. Interfaces cannot have instance variables, constructors, or methods with a body (other than default and static methods).

### Systax Differences Abstract vs Interface

|     | Abstract                                                     | Interface                                                                                          |
| --- | ------------------------------------------------------------ | -------------------------------------------------------------------------------------------------- |
|     | Declared using the abstract keyword.                         | Declared using the interface keyword.                                                              |
|     | Can have fields (attributes) with or without initial values. | Cannot have fields (attributes) with state; only constants (implicitly public, static, and final). |
|     | Can have constructors.                                       | Cannot have constructors.                                                                          |
|     | Can extend only one other class (abstract or concrete).      | Can extend multiple other interfaces.                                                              |
|     | Using extends keyword to access the abstract class.          | Using implements keyword to access the interface class                                             |

<br>

**Here is the example for abstract**

```java
abstract class Shape {
    String color; // Field

    // Constructor
    Shape(String color) {
        this.color = color;
    }

    // Abstract method (to be implemented by subclasses)
    abstract double area();

    // Concrete method
    void displayColor() {
        System.out.println("Color: " + color);
    }
}

// Concrete subclass of abstract class Shape
class Circle extends Shape {
    double radius; // Additional field

    // Constructor
    Circle(String color, double radius) {
        super(color); // Call to superclass constructor
        this.radius = radius;
    }

    // Implementation of abstract method
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
```

**Here is the example for interface**

```java
// Interface with constant, abstract, default, and static methods
interface Drawable {
    // Constant (implicitly public, static, final)
    String DEFAULT_COLOR = "Black";

    // Abstract method (implicitly public and abstract)
    void draw();

    // Default method with implementation
    default void display() {
        System.out.println("Displaying Drawable");
    }

    // Static method with implementation
    static void info() {
        System.out.println("Interface Drawable");
    }
}

// Class implementing the Drawable interface
class Rectangle implements Drawable {
    // Implementation of abstract method
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

    // Using the default method
    public void show() {
        display();
    }
}
```

<br>

### Purpose for implementing Abstract or Interface

#### Abstract

- Abstract classes are used to define a common base for related classes that share some implementation details.
- Abstract classes can provide default implementations of methods that subclasses can optionally override.
- Abstract classes help in reusing code among closely related classes, reducing duplication and promoting maintenance.

#### Interface

- Interfaces define a contract specifying methods that implementing classes must provide.
- Interfaces allow a class to inherit behaviors from multiple sources, overcoming Java's single inheritance limitation with classes.

Both method have a different functionality to be implemented, in situation where the class needs to inherite more than one behavior, it is good to use interfence since the interface can be implemented more than 1. On the other hand abstract is usefull if some classes only need one abstract that can represent all the other class.

## Assignment 2C

### FunctionalInterface in Java

```
@FunctionalInterface annotation is used to ensure that the functional interface can’t have more than one abstract method. In case more than one abstract methods are present, the compiler flags an ‘Unexpected @FunctionalInterface annotation’ message. However, it is not mandatory to use this annotation.
```

**Functional interfaces** are particularly useful in scenarios where you want to pass behavior (methods) as parameters, such as when using lambda expressions or method references. Here is the example of using lambda expressions below,

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Abstract method
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Using lambda expression to implement the functional interface
        Calculator adder = (a, b) -> a + b;

        // Using the functional interface method
        int result = adder.calculate(10, 5);
        System.out.println("Result of addition: " + result); // Output: 15
    }
}
```

####
