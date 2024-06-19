import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;

    public Employee(int employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Assignment5E {
    public static void main(String[] args) {
        // Step 1: Define Employee class

        // Step 2: Create a List of Employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2024001, "Ryan"));
        employees.add(new Employee(2024002, "Chandra"));
        employees.add(new Employee(2024003, "Hadi"));

        // Step 3: Convert List to Map with employeeID as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getEmployeeID,  -> emp));

        // Step 4: Sort the Map by keys in ascending order using a LinkedHashMap
        Map<Integer, Employee> sortedEmployeeMap = new LinkedHashMap<>();
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by key ascending
                .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));

        // Step 5: Print the sorted map
        sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
