package nl.bioinf.fooddiary.model.csvparser;

import com.opencsv.CSVReader;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductGroup;

import java.io.FileReader;
import java.io.IOException;

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
        String csvFile = "src/main/resources/csv/nevo_online_2019_Product.csv";

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
     * into groups for more readability and passes it on to the respective class.
     *
     * @param line, represents a single line and therefore a single product in the nevo_online_2019_Product.csv file.
     */
    public void parseCsvFile(String[] line) {
        int code = Integer.parseInt(line[2]);

        ProductGroup productGroup = ProductGroup.builder(Integer.parseInt(line[0]), line[1])
                .build();

        ProductDescription productDescription = ProductDescription.builder(line[3], line[4])
                .synonymous(line[5])
                .build();

        Product product = new Product(code, productGroup, productDescription);
        System.out.println(product.toString());

    }

    public static void main(String[] args) {
        ProductCsvParser csvParser = new ProductCsvParser();
        csvParser.readCsvFile();
    }
}
