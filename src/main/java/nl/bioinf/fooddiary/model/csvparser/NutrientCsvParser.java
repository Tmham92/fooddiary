package nl.bioinf.fooddiary.model.csvparser;

import com.opencsv.CSVReader;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Wagenaar
 * @version 0.0.2
 * date: 20-03-2020
 *
 * Parses the Nevo_Online_2019_Nutrient.csv file, this file consists out of a few columns, these columns are then
 * parsed into a nutrient object.
 */
public class NutrientCsvParser {
    /**
     * Read a csv file, in this case the nevo_online_2019_Nutrient.csv file. Pass every line separately on to the
     * parseCsvFile() method from the . An stack trace of the IOException is shown whenever the csv file isn't found or readable,
     * then stop the system.
     *
     * Catch: IOException, this could be a FileNotFoundException whenever the file isn't found.
     */
    public List<Nutrient> readCsvFile() {
        List<Nutrient> nutrientList = new ArrayList<>();
        String csvFile = "src/data/csv/nevo_online_2019_Nutrient.csv";

        // try to read every line in the csv file and pass each line individually on to the parseCsvFile() method
        try {
            // Make a CSVReader object that has a FileReader object with the csv file.
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int iteration = 0;

            while ((line = reader.readNext()) != null) {
                // Skip the first line in the csv file.
                if(iteration == 0) {
                    iteration++;
                    continue;
                }
                nutrientList.add(parseCsvFile(line));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }

        return nutrientList;
    }

    /**
     * This method receives every line in the Nevo_online_2019_Nutrient.csv file separately and parses each product line
     * separately into a Nutrient object.
     *
     * @param line, represents a single line and therefore a single nutrient in the Nevo_online_2019_Nutrient.csv file.
     */
    public Nutrient parseCsvFile(String[] line) {
        // Assign each value in the line to a variable for better readability.
        String nutrientCode = line[0];
        String nameDutch = line[1];
        String nameEnglish = line[2];
        String measurementUnit = line[3];

        // Parse every column into one single object for each individual line.
        Nutrient nutrient = Nutrient.builder(nutrientCode, nameDutch, nameEnglish, measurementUnit)
                .build();

        return nutrient;
    }
}
