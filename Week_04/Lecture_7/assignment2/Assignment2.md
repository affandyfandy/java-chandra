# Working with Annotations

In Spring, annotations are extensively used to configure and manage beans and their dependencies, making it easier to build and maintain applications.

## `EmailServiceImpl.java`

```java
    @Service("emailService")
    public class EmailServiceImpl implements EmailService {
    }
```

- @Service("emailService"): This annotation marks the class as a Spring service component with the bean name "emailService". This makes it eligible for component scanning and dependency injection.
- public class EmailServiceImpl implements EmailService: The class EmailServiceImpl implements the EmailService interface, indicating it must provide implementations for the methods declared in the EmailService interface.

<br>

```java
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Body : " + body);
        System.out.println("email sent ...");
        System.out.println("=========================");
    }
```

- @Override: This annotation indicates that sendEmail is overriding a method from the EmailService interface.
- public void sendEmail(String from, String to, String subject, String body): This method takes four parameters: from, to, subject, and body, which represent the sender, recipient, subject, and body of an email, respectively.
- System.out.println(...): These lines print the details of the email to the console: recipient, subject, body, and a confirmation message indicating the email was sent.

<br>

## `EmailService.java`

```java
    public interface EmailService {
        public void sendEmail(String to, String subject, String body);
    }
```

- The EmailService interface defines a contract for email-related operations, with methods for sending emails. Classes that implement this interface, such as EmailServiceImpl, must provide concrete implementations for both methods. This allows for a consistent API for email operations, regardless of the specific implementation details.

## `EmployeeService.java`

```java
@Service
public class EmployeeService {
    private String email;

    // Constructor injection with @Qualifier
    private final EmailService emailServiceConstructor;

    @Autowired
    public EmployeeService(@Qualifier("emailService") EmailService emailServiceConstructor) {
        this.emailServiceConstructor = emailServiceConstructor;
    }
```

- `email`: Fields for storing the employee's email.
- `emailServiceConstructor`: A final field for the EmailService instance injected via constructor injection.
- `Constructor`: The constructor is annotated with @Autowired and @Qualifier("emailService"), specifying that the emailServiceConstructor field should be injected with the EmailService bean named "emailService".

<br>

```java
        // Field injection with @Qualifier
        @Autowired
        @Qualifier("emailService")
        private EmailService emailServiceField;
```

- emailServiceField: Another instance of EmailService injected directly into the field using @Autowired and @Qualifier("emailService").

<br>

```java
        // Setter injection with @Qualifier
        private EmailService emailServiceSetter;
        
        @Autowired
        @Qualifier("emailService")
        public void setEmailServiceSetter(EmailService emailServiceSetter) {
            this.emailServiceSetter = emailServiceSetter;
        }   
```

- emailServiceSetter: Another instance of EmailService injected via a setter method. The setter method is annotated with @Autowired and @Qualifier("emailService").

<br>

## `MainCode.java`

```java
    @SpringBootApplication
    public class Assignment2Application {
    
        public static void main(String[] args) {
            ApplicationContext context = SpringApplication.run(Assignment2Application.class, args);
    
            EmployeeService employeeService1 = (EmployeeService) context.getBean("employeeService");
            employeeService1.setEmail("ryanfpt@gmail.com");
            EmailService emailService1 = (EmailService) context.getBean("emailService");
            emailService1.sendEmail(employeeService1.getEmail(), "Subject Test", "Body Test");
        }
    }
```

- ApplicationContext context: The Spring application context, which holds all the beans and their dependencies.
- SpringApplication.run(Assignment2Application.class, args): This line bootstraps the Spring Boot application, starting the Spring context and auto-scanning for components.
- context.getBean(employeeService): Retrieves the EmployeeService bean from the Spring application context.
- employeeService1.setEmail("ryanfpt@gmail.com"); Sets the email property of the EmployeeService instance.
- Emails are sent using three different dependency injection methods (constructor, field, and setter) to demonstrate the flexibility and capabilities of Spring's dependency injection mechanism.

# Compare three types Dependencies Injection

| Injection Method   | Pros                                                                     | Cons                                                                     |
| ------------------ | ------------------------------------------------------------------------ | ------------------------------------------------------------------------ |
| `Constructor`      | - Immutability: Dependencies are assigned at object creation time        | - Verbose: Constructor can become long if there are many dependencies    |
| `Injection`        | - Clear Dependencies: Clearly states required dependencies               |                                                                          |
|                    | - Easier Testing: Easy to create instances with mock dependencies        |                                                                          |
|                    | - Mandatory Dependencies: Ensures all required dependencies are provided |                                                                          |
| `Field Injection`  | - Simplicity: Quick and easy to set up                                   | - Hidden Dependencies: Not immediately obvious what the dependencies are |
|                    |                                                                          | - Testing Challenges: Requires reflection or DI framework for testing    |
|                    |                                                                          | - Immutability Issues: Dependencies can be changed after object creation |
| `Setter Injection` | - Optional Dependencies: Allows for optional dependencies                | - Hidden Dependencies: Not immediately obvious what the dependencies are |
|                    | - Flexibility: Dependencies can be changed after object creation         | - Testing Challenges: Requires additional setup in tests                 |
|                    |                                                                          | - Potential for Incomplete Initialization: Setter may not be called      |

## Recomendations

Constructor Injection is generally recommended as the best practice in modern Spring applications.

Why `Constructor Injection?`

- Clear Dependencies: All required dependencies are specified in the constructor, making it easy to see what the class depends on.
- Immutability: Once the object is created, its dependencies cannot change, leading to more stable and predictable code.
- Ease of Testing: Constructor injection makes it straightforward to provide mock dependencies in unit tests.
- Avoids Circular Dependencies: Constructor injection helps to avoid circular dependencies, which can be more challenging to detect and resolve with field or setter injection.

<br>

# Circular Dependency Injection

Circular dependency injection occurs when two or more beans (components) depend on each other, directly or indirectly, forming a cycle. This can cause issues in Spring because it tries to instantiate and inject beans in the correct order, but circular dependencies create a situation where each bean is waiting for another to be fully initialized, leading to a deadlock.

Here is the example of circular dependency happen,

```java
@Component
public class CircularDependencyA {

    private CircularDependencyB circB;

    @Autowired
    public CircularDependencyA(CircularDependencyB circB) {
        this.circB = circB;
    }
}
```

```java
@Component
public class CircularDependencyB {

    private CircularDependencyA circA;

    @Autowired
    public CircularDependencyB(CircularDependencyA circA) {
        this.circA = circA;
    }
}
```

`Problems Caused by Circular Dependencies`

- `Deadlock: Spring cannot resolve the dependencies because it cannot create an instance of either class without first having an instance of the other.
- Complexity: Circular dependencies can make the code harder to understand and maintain.
- Hidden Errors: It may lead to runtime errors that are difficult to trace and debug.

<br>

`Solutions to Circular Dependencies`

- Refactoring: The best solution is to refactor the code to remove the circular dependency. This might involve redesigning the classes or introducing a third class to break the dependency cycle.

- Setter Injection: Use setter injection instead of constructor injection. Spring can partially create the beans and then set the dependencies afterward. However, this is generally considered a workaround rather than a true solution.

- @Lazy Annotation: Use the @Lazy annotation to delay the initialization of one of the beans until it is actually needed.

```java
@Component
public class CircularDependencyA {

    private CircularDependencyB circB;

    @Autowired
    public CircularDependencyA(@Lazy CircularDependencyB circB) {
        this.circB = circB;
    }
}
```

```java
@Component
public class CircularDependencyB {

    private CircularDependencyA circA;

    @Autowired
    public CircularDependencyB(@Lazy CircularDependencyA circA) {
        this.circA = circA;
    }
}
```

the @Lazy annotation tells Spring to initialize ClassB lazily, breaking the circular dependency by delaying the instantiation of one of the beans until it is needed.

<br>

# Annotations Spring in Java

`@Bean`
<br>
Used to indicate that a method instantiates, configures, and initializes a new object to be managed by the Spring IoC container.

```java
@Bean
public MyBean myBean() {
    return new MyBean();
}
```

<br>

`@ComponentScan`
Configures component scanning directives for use with @Configuration classes. By default, it will scan the package of the class that declares this annotation.

```java
@Configuration
@ComponentScan(basePackages = "com.example.myapp")
public class AppConfig {
    // Configuration code here
}
```

<br>

`@Component, @Service, @Repository, @Controller`

<br>

```java
@Component
public class MyComponent {
    // Component code here
}
```

`@Component:` Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

<br>

```java
@Service
public class MyService {
    // Service code here
}
```

`@Service:` A specialization of @Component to indicate that a class is a service.

<br>

```java
@Repository
public class MyRepository {
    // Repository code here
}
```

`@Repository:` A specialization of @Component to indicate that a class is a repository (typically a DAO).

<br>

```java
@Controller
public class MyController {
    // Controller code here
}
```

`@Controller:` A specialization of @Component to indicate that a class is a controller (typically used in Spring MVC).

<br>

```java
@Component
public class MyComponent {

    @Autowired
    private MyService myService;

    // Component code here
}
```

`@Autowired:` Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.

<br>

```java
@Autowired
@Qualifier("specificBeanName")
private MyService myService;
```

`@Qualifier:` This annotation is used along with @Autowired to avoid confusion when multiple beans of the same type are present.
