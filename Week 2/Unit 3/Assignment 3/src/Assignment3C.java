import java.io.*;

public class Assignment3C {

    public static void main(String[] args) {
        String inputFileName = "test1.txt";
        String outputFileName = "test2.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}