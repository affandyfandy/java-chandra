class Subject {
    private String name;
    private String classId;

    public Subject(String name, String classId) {
        this.name = name;
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }
}

class Teacher {
    private String name;
    private int age;
    private Subject subject;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
        this.subject = null;
    }

    public void assignSubject(Subject subject) {
        this.subject = subject;
    }

    public void display() {
        System.out.println(
                "Teacher " + this.name + " teaching " + subject.getName() + " for Class " + subject.getClassId());
    }
}

public class Assignment1B {
    public static void main(String[] args) {
        Teacher T1 = new Teacher("Sri Mulyani", 25);
        Subject S1 = new Subject("Calculus", "C0001");

        T1.assignSubject(S1);
        T1.display();
    }
}
