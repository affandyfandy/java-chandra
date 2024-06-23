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

        // Find person with maximum age
        Persons oldestPerson = findMaxItem(persons, Persons::getAge);
        System.out.println("Person with maximum age: " + oldestPerson.getName() + ", " + oldestPerson.getAge());

        // Find person with maximum name (lexicographically)
        Persons personWithMaxName = findMaxItem(persons, Persons::getName);
        System.out.println(
                "Person with maximum name: " + personWithMaxName.getName() + ", " + personWithMaxName.getAge());
    }
}
