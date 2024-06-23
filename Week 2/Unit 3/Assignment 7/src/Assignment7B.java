import java.util.ArrayList;
import java.util.List;

public class Assignment7B {

    // Method to print elements of a list with an upper bounded wildcard
    public static void printList(List<? extends Number> list) {
        for (Number n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.1);
        doubles.add(2.2);
        doubles.add(3.3);

        // Print lists using the method with an upper bounded wildcard
        printList(integers);
        printList(doubles);
    }
}
