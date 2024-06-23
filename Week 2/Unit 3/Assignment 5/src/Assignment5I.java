import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Assignment5I {
    public static void main(String[] args) {
        // Step 1: Define Employee class

        // Step 2: Create a List of Employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2024001, "Ryan"));
        employees.add(new Employee(2024002, "Chandra"));
        employees.add(new Employee(2024003, "Hadi"));
        employees.add(new Employee(2024002, "Duplicate Chandra"));

        // Step 3: Convert List to Map with employeeID as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getEmployeeID, emp -> emp, (existing, replacement) -> existing));

        // Step 4: Sort the Map by keys in ascending order using a LinkedHashMap
        Map<Integer, Employee> sortedEmployeeMap = new LinkedHashMap<>();
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by key ascending
                .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));

        // Step 5: Print the sorted map
        sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));

        // Step 6: Add Employees to HashSet and demonstrate that duplicates are
        // recognized
        Set<Employee> employeeSet = new HashSet<>(employees);
        System.out.println("\nHashSet contents:");
        employeeSet.forEach(System.out::println);
    }
}
