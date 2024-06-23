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
