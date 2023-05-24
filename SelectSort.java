import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A program that performs selection sort on each line of input read from a file
 * and writes the sorted output to another file.
 *
 * @author Logan S
 *
 * @version 1.0
 * @since 2023-05-22
 */
public final class SelectSort {

    /**
     * Reads the input from a file and returns a list of lists containing the
     * parsed integers.
     *
     * @param inputFile The path of the input file.
     * @return A list of lists containing parsed integers from input file.
     * @throws IOException If an error occurs while reading the input file.
     */

    public static List<List<Integer>> readInput(String inputFile)
            throws IOException {
        final List<List<Integer>> inputData = new ArrayList<>();
        final Scanner scanner = new Scanner(new File(inputFile));

        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine().trim();
            final String[] numbers = line.split("\\s+");

            final List<Integer> row = new ArrayList<>();
            for (String number : numbers) {
                try {
                    final int num = Integer.parseInt(number);
                    row.add(num);
                } catch (NumberFormatException exception) {
                    System.out.println("Error " + exception.getMessage()
                            + ". Skipping.");
                    break;
                }
            }

            if (!row.isEmpty()) {
                inputData.add(row);
            }
        }

        scanner.close();
        return inputData;
    }

    /**
     * Sorts the data in each list using the selection sort algorithm.
     *
     * @param inputData The input data containing lists of integers.
     * @return A list of lists with sorted integers.
     */
    public static List<List<Integer>> sortData(List<List<Integer>> inputData) {
        final List<List<Integer>> sortedData = new ArrayList<>();

        for (List<Integer> row : inputData) {
            final Integer[] arr = row.toArray(new Integer[0]);
            selectionSort(arr);
            sortedData.add(Arrays.asList(arr));
        }

        return sortedData;
    }

    /**
     * Performs selection sort on the given array of integers.
     *
     * @param arr The array to be sorted.
     */
    public static void selectionSort(Integer[] arr) {
        final int arrNum = arr.length;

        for (int i = 0; i < arrNum - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrNum; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            final int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Writes the sorted data to an output file.
     *
     * @param outputFile The path of the output file.
     * @param sortedData The sorted data to be written.
     * @throws IOException If an error occurs while writing the output file.
     */
    public static void writeOutput(String outputFile,
            List<List<Integer>> sortedData) throws IOException {
        final PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

        for (List<Integer> row : sortedData) {
            for (int i = 0; i < row.size(); i++) {
                writer.print(row.get(i));
                if (i != row.size() - 1) {
                    writer.print(" ");
                }
            }
            writer.println();
        }

        writer.close();
    }

    /**
     * The entry point of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        final String inputFile = "input.txt";
        final String outputFile = "output.txt";

        try {
            // Read the input data from the input file
            final List<List<Integer>> inputData = readInput(inputFile);

            // Sort the data using selection sort algorithm
            final List<List<Integer>> sortedData = sortData(inputData);

            // Write the sorted data to the output file
            writeOutput(outputFile, sortedData);

            System.out.println("Sorting completed successfully.");
        } catch (IOException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
}
