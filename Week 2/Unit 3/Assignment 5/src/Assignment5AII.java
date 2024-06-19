import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Assignment5AII {
    public static void main(String[] args) {
        // HashSet example
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Cat");
        hashSet.add("Dog");
        hashSet.add("Bird");
        System.out.println("HashSet: " + hashSet);

        // TreeSet example
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Cat");
        treeSet.add("Dog");
        treeSet.add("Bird");
        System.out.println("TreeSet: " + treeSet);

        // LinkedHashSet example
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Cat");
        linkedHashSet.add("Dog");
        linkedHashSet.add("Bird");
        System.out.println("LinkedHashSet: " + linkedHashSet);
    }
}