import java.util.HashMap;

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
