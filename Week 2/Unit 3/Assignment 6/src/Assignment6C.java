import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Assignment6C {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.csv"; // Replace with your input CSV file path
        String outputFile = "output.csv"; // Replace with your output CSV file path
        String keyFieldName = "id"; // Replace with the name of the key field in the CSV

        // HashSet to store seen keys
        Set<String> seenKeys = new HashSet<>();

        // Open input file and output writer
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            // Read header line
            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            // Find key field index in header
            String[] headers = header.split(",");
            int keyIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase(keyFieldName)) {
                    keyIndex = i;
                    break;
                }
            }
            if (keyIndex == -1) {
                throw new IllegalArgumentException("Key field not found in CSV header: " + keyFieldName);
            }

            // Process each line in the CSV
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1); // Split line into fields

                // Extract key from the line based on keyIndex
                String key = fields[keyIndex].trim();

                // Write to output file if key is not seen before
                if (!seenKeys.contains(key)) {
                    writer.write(line);
                    writer.newLine();
                    seenKeys.add(key);
                }
            }
        }
    }
}
