import java.util.ArrayList;
import java.util.List;

public class Assignment5K {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        data.add("Joe");
        data.add("Hellen");
        data.add("Test");
        data.add("Rian");
        data.add("Ruby");

        System.out.println("Before Removing");
        for (String d : data) {
            System.out.println(d);
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == "Test") {
                data.remove(i);
            }
        }
        System.out.println("\nAfter Removing");
        for (String d : data) {
            System.out.println(d);
        }
    }
}
