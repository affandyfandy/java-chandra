import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Assignment5A {
    public static void main(String[] args) {
        // ArrayList example
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Horse");
        arrayList.add("Cat");
        arrayList.add("Dog");
        System.out.println("ArrayList: " + arrayList);

        // LinkedList example
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.add("Cat");
        linkedList.add("Horse");
        System.out.println("LinkedList: " + linkedList);
    }
}