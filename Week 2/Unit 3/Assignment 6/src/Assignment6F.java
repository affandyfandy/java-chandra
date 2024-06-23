import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment6F {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));
        // Convert list of employees to map with ID as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, employee -> employee));

        // Print the map
        employeeMap.forEach((id, employee) -> System.out.println("ID: " + id + ", Employee: " + employee));
    }
}
