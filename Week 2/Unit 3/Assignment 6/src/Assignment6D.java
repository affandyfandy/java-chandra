import java.util.Arrays;
import java.util.List;

public class Assignment6D {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
        String startingLetter = "G";

        long count = strings.stream()
                .filter(str -> str.startsWith(startingLetter))
                .count();

        System.out.println("Number of strings starting with \"" + startingLetter + "\": " + count);
    }
}
