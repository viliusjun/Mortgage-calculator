/*
CSVFile class that creates and writes the table's calculated data into a CSV File
 */
package CSV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFile {

    private static final String SEMICOLON = ";";
    private static final String DEFAULT_SEPARATOR = SEMICOLON;
    private static final String DOUBLE_QUOTES = "\"";
    private static final String EMBEDDED_DOUBLE_QUOTES = "\"\"";
    private static final String NEW_LINE_UNIX = "\n";
    private static final String NEW_LINE_WINDOWS = "\r\n";

    //public static void main(String[] args) throws IOException {
    public static void createCSV(String[] monthNumber, String[] sum, String[] payment, String[] percent, String[] leftToPay, int number) {

        CSVFile writer = new CSVFile();
        writer.writeToCsvFile(createCsvDataSpecial(), createCsvDataSpecial(monthNumber, sum, payment, percent, leftToPay, number), new File("MortgageData.csv"));

    }

    public String convertToCsvFormat(final String[] line) {
        return convertToCsvFormat(line, DEFAULT_SEPARATOR);
    }

    public String convertToCsvFormat(final String[] line, final String separator) {
        return convertToCsvFormat(line, separator, true);
    }

    // if quote = true, all fields are enclosed in double quotes
    public String convertToCsvFormat(
            final String[] line,
            final String separator,
            final boolean quote) {

        return Stream.of(line)                              // convert String[] to stream
                .map(l -> formatCsvField(l, quote))         // format CSV field
                .collect(Collectors.joining(separator));    // join with a separator

    }

    // put your extra login here
    private String formatCsvField(final String field, final boolean quote) {

        String result = field;

        if (result.contains(SEMICOLON)
                || result.contains(DOUBLE_QUOTES)
                || result.contains(NEW_LINE_UNIX)
                || result.contains(NEW_LINE_WINDOWS)) {

            // if field contains double quotes, replace it with two double quotes \"\"
            result = result.replace(DOUBLE_QUOTES, EMBEDDED_DOUBLE_QUOTES);

            // must wrap by or enclosed with double quotes
            result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;

        } else {
            // should all fields enclosed in double quotes
            if (quote) {
                result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;
            }
        }

        return result;

    }

    // a standard FileWriter, CSV is a normal text file
    private void writeToCsvFile(List<String[]> list, List<String[]> list2, File file) {

        List<String> collect = list.stream()
                .map(this::convertToCsvFormat)
                .collect(Collectors.toList());

        List<String> collect2 = list2.stream()
                .map(this::convertToCsvFormat)
                .collect(Collectors.toList());

        // CSV is a normal text file, need a writer
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : collect) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : collect2) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String[]> createCsvDataSpecial() {

        String[] header = {"Month Number", "Sum", "Payment", "Percent", "Left To Pay"};

        List<String[]> list = new ArrayList<>();
        list.add(header);

        return list;

    }

    public static List<String[]> createCsvDataSpecial(String[] monthNumber, String[] sum, String[] payment, String[] percent, String[] leftToPay, int number) {
        String[][] all = new String[number][];
        String[] record;

        for (int i = 0; i < number; ++i) {
            record = new String[]{monthNumber[i], sum[i], payment[i], percent[i], leftToPay[i]};
            all[i] = record;
        }

        String[] header = {"Month Number", "Sum", "Payment", "Percent", "Left To Pay"};

        List<String[]> list = new ArrayList<>();

        list.add(header);

        for (int i = 0; i < number; ++i){
            list.add(all[i]);
        }

        return list;

    }

}
