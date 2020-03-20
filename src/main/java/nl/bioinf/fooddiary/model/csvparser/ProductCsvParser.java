package nl.bioinf.fooddiary.model.csvparser;

import com.opencsv.CSVReader;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * Parses the nevo_online_2019_Product.csv file, this file consists out of products, a single product in the table
 * has a lot of columns. There are columns that give information about: code, the group the product is in, a description
 * of that product, how it is measured and many columns that have the nutrient value for that product. This file is
 * read and then parsed. The columns are divided into groups to ensure readability, then the information is grouped and
 * passed on to different classes representing those groups.
 */
public class ProductCsvParser {

    /**
     * Read a csv file, in this case the nevo_online_2019_Product.csv file. Pass every line separately on to the
     * parseCsvFile() method. An stack trace of the IOException is shown whenever the csv file isn't found or readable,
     * then stop the system.
     *
     * Catch: IOException, this could be a FileNotFoundException whenever the file isn't found.
     */
    public void readCsvFile() {
        String csvFile = "src/data/csv/nevo_online_2019_Product.csv";

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
                parseCsvFile(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method receives every line in the nevo_online_2019_Product.csv file separately and groups the columns
     * into groups for more readability and passes it on to classes that represent those groups, those classes store
     * the information and return objects. These objects are then passed on to the Product class that stores all
     * the information for a single product in the Nevo_Online_2019 file.
     *
     * @param line, represents a single line and therefore a single product in the nevo_online_2019_Product.csv file.
     */
    public Product parseCsvFile(String[] line) {
        int code = Integer.parseInt(line[2]);

        // Parse the group code and group description into an object.
        ProductGroup productGroup = ProductGroup.builder(Integer.parseInt(line[0]), line[1])
                .build();

        // Parse the descriptionDutch, descriptionEnglish and synonymous of the product into an object.
        ProductDescription productDescription = ProductDescription.builder(line[3], line[4])
                .synonymous(line[5])
                .build();

        // Parse the measurement -Unit, -Quantity and -Comment into an object.
        ProductMeasurement productMeasurement = ProductMeasurement.builder(line[6], Integer.parseInt(line[7]))
                .measurementComment(line[8])
                .build();

        // Parse the enrichedWith and tracesOf values from a the product into an object.
        ProductInfoExtra productInfoExtra = ProductInfoExtra.builder()
                .enrichedWith(line[9])
                .tracesOf(line[10])
                .build();


        // Parse all nutrient values from a single product line into one object.
        NutrientValues nutrientValues = NutrientValues.builder()
                .nutrientValue(Arrays.copyOfRange(line, 11, line.length))
                .build();

        // Pass on all the objects on to the Product class, this class stores all the objects into one single object.
        Product product = new Product(code, productGroup, productDescription, productMeasurement,
                productInfoExtra, nutrientValues);

        return product;
    }
}
