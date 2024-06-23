import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment6B {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Chandra", "Ryan", "Chandra", "Hadi", "Ryan", "Rimba");

        // Remove duplicates using stream
        List<String> distinctStrings = strings.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("List with duplicates removed: " + distinctStrings);
    }
}
