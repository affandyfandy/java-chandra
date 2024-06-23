import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    // Constructor
    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    // toString method to print Employee details
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class Assignment6E {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ada Wong", 30, 5000000),
                new Employee(2, "Zaria Onths", 25, 6800000),
                new Employee(3, "Simalakama", 35, 4950000),
                new Employee(4, "Rinda Lesmana", 28, 11000000));

        // Sorting names alphabetically in ascending order
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee -> System.out.println(employee.getName()));

        // Finding employee with max salary
        Optional<Employee> maxSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        maxSalaryEmployee
                .ifPresent(employee -> System.out.println("\nEmployee with max salary: " + employee.getName()));

        // Specific keywords to check
        String[] keywords = { "Rinda" };

        // Check if any employee names match specific keywords
        boolean matchFound = employees.stream()
                .anyMatch(employee -> Arrays.stream(keywords)
                        .anyMatch(keyword -> employee.getName().contains(keyword)));

        System.out.println("\nMatch found: " + matchFound);
    }
}
