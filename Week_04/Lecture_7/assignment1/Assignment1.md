# Advantages of Dependency Injection (DI) 

`Dependency Injection (DI)` is a design pattern used in software engineering to implement Inversion of Control (IoC) principle, where the dependencies of a class are injected from the outside rather than created internally.

In simpler terms, DI allows programs to remove the responsibility of creating dependent objects (dependencies) from your classes. Instead of a class creating its own dependencies, these dependencies are provided to the class from an external source, typically a framework like Spring in Java.

There are three types of common types of dependency injection

- `Constructor Injection:` Dependencies are provided to the client class through the constructor. This ensures that all required dependencies are provided when the object is instantiated.

- `Setter Injection:` Dependencies are set through setter methods on the client class. This allows flexibility by making dependencies optional, but it requires clients to manage potentially incomplete states.

- `Field Injection:` Dependencies are injected directly into public fields of the client class. While convenient, it makes dependencies less visible and harder to test or enforce immutability.

Here are the advantages and the drawbacks of using `DI` in java.

| Advantages                                     | Drawbacks                                               |
|------------------------------------------------|----------------------------------------------------------|
| **Loose Coupling**: Promotes loose coupling between classes by removing dependency instantiation from client code. | **Complexity**: Introduces complexity, especially in larger projects or with intricate dependency graphs. |
| **Easier Testing**: Facilitates easier unit testing by allowing dependencies to be mocked or stubbed easily. | **Runtime Errors**: Errors related to DI may only manifest at runtime, such as misconfigurations or circular dependencies. |
| **Promotes Reusability**: Enhances reusability as classes can be configured with different implementations of dependencies. | **Overhead**: Introduces some performance and memory overhead due to runtime dependency resolution and injection. |
| **Simplifies Refactoring**: Changes in dependencies or their implementations do not require modifications to client code, simplifying refactoring. | **Learning Curve**: Requires learning the DI principles and specific frameworks, which can have a steep learning curve. |
| **Enhanced Modularization**: Encourages modularization of code by clearly defining dependencies and their interactions. | **Potential Misuse**: Improper use can lead to overly complex code that is difficult to maintain and understand. |

<br>

# Create Employee using java configuration

```java
@Component
public class Employee {
    private int id = 1;
    private String name = "John Doe";
    private int age = 30;
    private final EmployeeWork employeeWork;
    
    // Constructor Injection
    @Autowired
    public Employee(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
    }
    

    public void working() {
        System.out.println("Employee Details: " + this.toString());
        employeeWork.work();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
```
## Explanations

- `Class Declaration and Fields:`
    - Employee class is marked with `@Component`, indicating it's a Spring-managed component and eligible for auto-detection and auto-configuration.
    - It has private fields id, name, age, and employeeWork.
    - employeeWork is marked as final, indicating it's initialized once and cannot be changed afterwards.

<br>

- `Constructor Injection:`
    - The class uses constructor injection with `@Autowired`. This means when an Employee instance is created, Spring will automatically inject an instance of EmployeeWork into it.
    - Constructor injection is a preferred way to inject dependencies in Spring, promoting clearer code and easier testing.

<br>

- `working() Method:`
    - This method prints details of the Employee object (id, name, age) using the overridden toString() method.
    - It then calls the work() method on the injected EmployeeWork instance (employeeWork). This demonstrates collaboration between Employee and EmployeeWork components.

<br>

After we create an employee model, we create the service called employeeWork, here is the example of the code,
```java
@Component
public class EmployeeWork {

    public void work(){
        System.out.println("Working ... ");
    }
}
```
## Explanations

- `Class Declaration and Method:`
    - EmployeeWork is marked with `@Component`, indicating it's a Spring-managed component and can be automatically discovered and wired by Spring.
    - It has a single method work() which, when called, prints "Working ... " to the console.

<br>

- `Purpose:`
    - The class encapsulates the behavior related to an employee's work action.
    - In a real-world scenario, this class could contain more complex logic related to work tasks, calculations, or interactions with other components or services.
  
The last is to create the main programs like shown below,

```java
@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Assignment1Application.class, args);

		Employee employee  = context.getBean(Employee.class);
		employee.working();
	}

}
```
## Explanations
- `Annotation @SpringBootApplication:`
    - This annotation is used to mark a configuration class that declares one or more `@Bean` methods and also triggers auto-configuration and component scanning. It's a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes.
    - It's typically placed on your main class, which is usually the entry point of your Spring Boot application.

<br>

- `main Method:`
    - This is the standard Java main method that serves as the entry point for your application.
    - SpringApplication.run(Assignment1Application.class, args); starts the Spring Boot application.
    - It returns an ApplicationContext object, which represents the Spring IoC container. It manages beans and their dependencies.

<br>

- `Getting and Using a Bean:`
    - Employee employee = context.getBean(Employee.class); retrieves an instance of the Employee bean from the Spring IoC container. The Employee class is annotated with @Component, so Spring manages it as a bean.
    - employee.working(); calls the working() method on the Employee instance retrieved from the container. This method internally prints employee details and delegates work functionality to the EmployeeWork component.