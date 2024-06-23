import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class Employee5J {
    private int employeeID;
    private String name;
    private String department; // Add department

    public Employee5J(int employeeID, String name, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class CompositeKey {
    private String department;
    private int employeeID;

    public CompositeKey(String department, int employeeID) {
        this.department = department;
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return department;
    }

    public int getEmployee() {
        return employeeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CompositeKey that = (CompositeKey) obj;
        return employeeID == that.employeeID && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employeeID);
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "department='" + department + '\'' +
                ", employeeID=" + employeeID +
                '}';
    }
}

public class Assignment5J {
    public static void main(String[] args) {
        // Step 1: Define Employee class

        // Step 2: Create a List of Employees
        List<Employee5J> employees = new ArrayList<>();
        employees.add(new Employee5J(2024001, "Ryan", "HR"));
        employees.add(new Employee5J(2024002, "Chandra", "IT"));
        employees.add(new Employee5J(2024003, "Hadi", "Finance"));
        employees.add(new Employee5J(2024002, "Duplicate Chandra", "IT")); // Duplicate in the same department

        // Step 3: Convert List to Map with CompositeKey (department, employeeID) as key
        Map<CompositeKey, Employee5J> employeeMap = employees.stream()
                .collect(Collectors.toMap(
                        emp -> new CompositeKey(emp.getDepartment(), emp.getEmployeeID()),
                        emp -> emp,
                        (existing, replacement) -> existing));

        // Step 4: Sort the Map by keys in ascending order using a LinkedHashMap
        Map<CompositeKey, Employee5J> sortedEmployeeMap = new LinkedHashMap<>();
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((key1, key2) -> {
                    int deptCompare = key1.getDepartment().compareTo(key2.getDepartment());
                    if (deptCompare == 0) {
                        return Integer.compare(key1.getEmployee(), key2.getEmployee());
                    } else {
                        return deptCompare;
                    }
                })) // Sort by department then by employeeID
                .forEachOrdered(entry -> sortedEmployeeMap.put(entry.getKey(), entry.getValue()));

        // Step 5: Print the sorted map
        sortedEmployeeMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
