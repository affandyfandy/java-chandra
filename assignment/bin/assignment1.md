# Value Types vs Reference Types:

#### Question Compare: Value types and reference types. Explain and example:

**Primitive data** are data types that store their values directly in the memory location where the variable is declared. When operated upon, the values of value types are directly manipulated. In value types, when a variable is declared, memory is allocated to store the actual value of that data type.

Example of value types in Java are _primitive data_ types such as `int`.

```java
public class assignment1 {
    public static void main(String[] args) {
        int number = 90;
        System.out.println("Before method call: " + number); // Output: 90
        modifyValue(number);
        System.out.println("After method call: " + number); // Output: 90
    }

    public static void modifyValue(int num) {
        num = 100;
    }
}

```

<br>

**Reference Type** one of the kind of variable that store a data from the actual memory location.

Example of value types in Java are _reference type_ types such as create a string Builder.

```java
class NinjaEquip {
    String equipment;

    NinjaEquip(String equipment) {
        this.equipment = equipment;
    }
}

public class assignment1 {
    public static void main(String[] args) {
        NinjaEquip ninjaEquip = new NinjaEquip("Swords");
        System.out.println("Before method call: " + ninjaEquip.equipment); // Output: Swords
        modifyReference(ninjaEquip);
        System.out.println("After method call: " + ninjaEquip.equipment); // Output: Shurikens
    }

    public static void modifyReference(NinjaEquip equip) {
        equip.equipment = "Shurikens";
    }
}
```

<br>

# Characteristic Primitive Data and Reference Data

|                           | Primitive Data                                                                           | Reference Data                                                                        |
| ------------------------- | ---------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| **Storage**               | Store values directly in memory.                                                         | Store references (memory addresses) to the actual objects.                            |
| **Memory Usage**          | Typically more memory-efficient and faster to access due to fixed Data.                  | Have a complex memory usage since they refer to objects that can vary in size.        |
| **How to Handle Values**  | If we assign a variable to another variable, they are not refering to previous variable. | If we assign a variable to another variable, they will refering to previous variable. |
| **Passing as Parameters** | A copy of the value, when the copy value change it's not affect the original variable.   | A copy of the reference, when the copy value change it's affect the original object.  |
