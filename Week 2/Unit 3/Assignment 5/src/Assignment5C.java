import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment5C {
    public static void main(String[] args) {

        String inputFile = "input.csv";
        String outputFile = "output.csv";

        // Replace this with the column name for the key field in the CSV
        String keyField = "ID"; // Example: "ID" is the key field

        try {
            // Map to store occurrences of each key
            Map<String, Integer> keyCountMap = new HashMap<>();

            // Buffered reader for reading input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Buffered writer for writing output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Read the header line (assuming the first line is header)
            String header = reader.readLine();
            writer.write(header + "\n"); // Write header to output file

            // Read each line from input file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line by comma (CSV assumption)
                String[] parts = line.split(",");

                // Extract key value based on keyField index (adjust accordingly)
                int keyFieldIndex = -1;
                for (int i = 0; i < parts.length; i++) {
                    if (header.split(",")[i].trim().equalsIgnoreCase(keyField.trim())) {
                        keyFieldIndex = i;
                        break;
                    }
                }

                if (keyFieldIndex != -1) {
                    String keyValue = parts[keyFieldIndex].trim();

                    // Update count of key occurrences
                    keyCountMap.put(keyValue, keyCountMap.getOrDefault(keyValue, 0) + 1);
                }
            }

            // Close resources
            reader.close();

            // Reopen the reader to start from the beginning
            reader = new BufferedReader(new FileReader(inputFile));
            // Skip the header line
            reader.readLine();

            // Read each line from input file again to write non-duplicate lines
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String keyValue = parts[getIndex(header, keyField)].trim();

                // Check if the key value occurs only once
                if (keyCountMap.get(keyValue) == 1) {
                    writer.write(line + "\n");
                }
            }

            // Close resources
            reader.close();
            writer.close();

            System.out.println("Filtered output written to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the index of a field in the CSV header
    private static int getIndex(String header, String field) {
        String[] headers = header.split(",");
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equalsIgnoreCase(field.trim())) {
                return i;
            }
        }
        return -1;
    }
}
