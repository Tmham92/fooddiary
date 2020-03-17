package nl.bioinf.fooddiary.model.csvparser;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
public class CsvParserProduct {
    // Instance variable declaration

    public void csvReader() {
        String csvFile = "/homes/twagenaar/Jaar3/NevoTabel/nevo_online_2019_Product.csv";

        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println(line[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CsvParserProduct parser = new CsvParserProduct();
        parser.csvReader();
    }
}
