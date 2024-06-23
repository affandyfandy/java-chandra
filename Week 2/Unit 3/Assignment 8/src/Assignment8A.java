import java.io.Serializable;

// A serializable class with serialVersionUID explicitly defined
class MyClass implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private String name;
    private int age;

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

public class Assignment8A {
    public static void main(String[] args) {
        // Serialization
        try {
            MyClass obj = new MyClass("John Doe", 30);
            // Serialization code here
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserialization
        try {
            MyClass obj;
            // Deserialization code here
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}