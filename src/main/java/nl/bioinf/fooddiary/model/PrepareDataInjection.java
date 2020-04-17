package nl.bioinf.fooddiary.model;

import nl.bioinf.fooddiary.model.csvparser.NutrientCsvParser;
import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Class that is used to prepare the data for the product_nutrient table in the fooddiary database. The databse has
 * a different structure then the nevo file, therefore the data from the nevo file needs to be adjusted accordingly
 * to the structure of the foodidary database. This class uses the product and the nutrient parser to get the data
 * out of the nevo file and store them into JavaBeans objects. Those objects are then added to a list that represent
 * those objects. Those lists are then passed to methods that retrieve and rearrange the data so that is fit into
 * the product_nutrient table.
 */
public class PrepareDataInjection {

    /**
     * Start the process of rearranging the data. Call the product and nutrient parsers to get the data out of the
     * nevo file and pass those lists containing the objects to the rearrangeDataLists method.
     * @return List<List<ProductNutrient>>, A list containing list containing product nutrient data.
     */
    public List<List<ProductNutrient>> startRearrangeData() {
        ProductCsvParser productParser = new ProductCsvParser();
        List<Product> productList = productParser.readCsvFile();

        NutrientCsvParser nutrientParser = new NutrientCsvParser();
        List<Nutrient> nutrientList = nutrientParser.readCsvFile();

        List<List<ProductNutrient>> productNutrientData = rearrangeDataLists(productList, nutrientList);
        return productNutrientData;
    }

    /**
     * Method that gets the a list containing every product in the product table from the nevo file. For every product
     * in that list pass it on to the mergeNutrientData method, this method also receives a list containing all the
     * nutrients in the nutrient table from the nevo table. The mergeNutrientData method returns a list containing
     * ProductNutrient data, see the model/nutrient/ProductNutrient for more information about this object.
     * @param productList List of products from the nevo file
     * @param nutrientList List of nutrient from the nevo file
     * @return List<List<ProductNutrient>>, A list containing list containing product nutrient data.
     */
    public List<List<ProductNutrient>> rearrangeDataLists(List<Product> productList, List<Nutrient> nutrientList) {
        // List containing all the rearranged ProductNutrient data.
        List<List<ProductNutrient>> productNutrientData = new ArrayList<>();

        // For every product separately with the nutrientList on to the mergeNutrientData method.
        for (Product product:productList) {
            List<ProductNutrient> productNutrientList = mergeNutrientData(product, nutrientList);
            productNutrientData.add(productNutrientList);
        }

        return productNutrientData;
    }

    /**
     * Method that rearranges the data for a single product into a ProductNutrient object using the nutrients in the
     * NutrientList. A single product has multiple nutrient values, those values correspond to the nutrients in the
     * nutrients list. The data is then rearranged into a ProductNutrient object.
     * @param product Product object
     * @param nutrientList List of all the nutrients in the nevo_online_2019_nutrient.csv file
     * @return List<ProductNutrient> List containing one single product with multiple nutrient values.
     */
    public static List<ProductNutrient> mergeNutrientData(Product product, List<Nutrient> nutrientList) {
        List<ProductNutrient> productNutrientList = new ArrayList<>();

        // For every nutrient in nutrientList
        for (int index = 0; index < nutrientList.size(); index++) {

            // get the nutrienCode and nutrienValue corresponding to that code.
            String nutrientCode = nutrientList.get(index).getNutrientCode();
            String nutrientValue = product.getNutrientValues().getNutrients().get(index).getValue();

            // add the productCode, nutrientCode and nutrientValue to a ProductNutrient object.
            ProductNutrient productNutrient = ProductNutrient.builder(product.getCode(), nutrientCode, nutrientValue)
                    .build();

            // Add a single product nutrient object to the productNutrient list.
            productNutrientList.add(productNutrient);
        }
        return productNutrientList;
    }
}
