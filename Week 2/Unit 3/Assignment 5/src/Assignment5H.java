import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

public class Assignment5H {
    public static void main(String[] args) {
        Set<Persons> people = new HashSet<>();
        people.add(new Persons("Alice", 30));
        people.add(new Persons("Bob", 25));
        people.add(new Persons("Alice", 30));

        // The set should contain only two unique Personss because Alice with age 30 is
        // added twice
        System.out.println("Number of unique Personss: " + people.size()); // Output: 2

        // Print the unique Personss in the set
        for (Persons Persons : people) {
            System.out.println(Persons);
        }
    }
}
