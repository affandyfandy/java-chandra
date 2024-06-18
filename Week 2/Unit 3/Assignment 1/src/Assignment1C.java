import java.util.ArrayList;
import java.util.List;

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

class Student {
    private String name;
    private int age;
    private List<Subject> subjects;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.subjects = new ArrayList<>();
    }

    public void Learn(Subject subject) {
        this.subjects.add(subject);
    }

    public String getName() {
        return this.name;
    }

    public void Display() {
        System.out.print("Student " + this.name + " is learning ");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.print(subjects.get(i).getName());
            if (i < subjects.size() - 1) {
                System.out.print(", ");
            }
        }
    }

}

public class Assignment1C {
    public static void main(String[] args) {
        Teacher T1 = new Teacher("Sri Mulyani", 25);
        Subject S1 = new Subject("Calculus", "C0001");
        Student ST1 = new Student("Rano Karno", 18);

        T1.assignSubject(S1);
        ST1.Learn(S1);
        ST1.Display();
    }
}
