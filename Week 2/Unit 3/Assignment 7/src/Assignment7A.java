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

    // Generic method to remove duplicates from a list based on specified key
    // extractors
    @SafeVarargs
    public static <T> List<T> distinctByKeys(List<T> list, Function<? super T, ?>... keyExtractors) {
        Set<List<?>> seen = new HashSet<>();
        return list.stream()
                .filter(t -> {
                    List<?> keys = Arrays.stream(keyExtractors)
                            .map(ke -> ke.apply(t))
                            .collect(Collectors.toList());
                    return seen.add(keys);
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Create a list of Person objects with duplicates
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 30));
        persons.add(new Person("Bob", 25));
        persons.add(new Person("Alice", 30)); // Duplicate
        persons.add(new Person("Charlie", 35));
        persons.add(new Person("Bob", 25)); // Duplicate
        persons.add(new Person("David", 28));
        persons.add(new Person("Eve", 32));

        // Remove duplicates based on name
        List<Person> distinctByName = distinctByKeys(persons, Person::getName);
        System.out.println("Distinct by name:");
        distinctByName.forEach(person -> System.out.println(person.getName() + ", " + person.getAge()));

    }
}
