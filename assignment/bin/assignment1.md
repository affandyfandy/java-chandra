# Value Types vs Reference Types:

#### Question Compare: Value types and reference types. Explain and example:

**Primitive data** are data types that store their values directly in the memory location where the variable is declared. When operated upon, the values of value types are directly manipulated. In value types, when a variable is declared, memory is allocated to store the actual value of that data type.

Example of value types in Java are _primitive data_ types such as `int`, `double`, `boolean`.

```java
public class assignment1 {
    public static void main(String[] args) {
        int Ex1 = 90;
        double Ex2 = 9.9;
        boolean Ex3 = true;
    }

```

<br>

**Reference Type** one of the kind of variable that store a data from the actual memory location.

Example of value types in Java are _reference type_ types such as create a string Builder.

```java
public class assignment1 {
    public static void main(String[] args) {
        NinjaEquip N1 = new NinjaEquip("Swords");
        NinjaEquIp N2 = N1;

    }
}
```

The code above is to explain that the N2 Variables is attach by N1 address and the difference between privitive and references type is the allocation memory where the primitive is gaining a new memory to store but the reference still save into the same memory.
