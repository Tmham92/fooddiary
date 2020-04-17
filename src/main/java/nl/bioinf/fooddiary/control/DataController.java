package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.NutrientRepository;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.ProductNutrientRepository;
import nl.bioinf.fooddiary.model.PrepareDataInjection;
import nl.bioinf.fooddiary.model.csvparser.NutrientCsvParser;
import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Data controller that serves the /data url, when called the data from the parsers is inserted into the database.
 * For this process the repositories interfaces from the DAO's are used to create sql queries that support inserting
 * the data.
 */
@Controller
public class DataController {

    // Logger object used for logging the process.
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private ProductNutrientRepository productNutrientRepository;

    private List<Product> productList = new ProductCsvParser().readCsvFile();
    private List<Nutrient> nutrientList = new NutrientCsvParser().readCsvFile();
    private List<List<ProductNutrient>> productNutrientData = new PrepareDataInjection().startRearrangeData();

    /**
     * Method that uses the productRepository, nutrientRepository and Product_Nutrient repository. These repositories
     * are used to call the methods in the DAO respectively to those repositories for inserting data from the
     * productCsvParser, NutrientCsvParser and productNutrientData classes.
     * @return
     */
    @RequestMapping(value = "/data")
    public String insertNevoData() {
        logger.info("/data url has been called, inserting data into the database.");


        logger.info("Inserting product data into the product table.");
        for (Product product:productList) {

            productRepository.insertProductData(product);
        }
        logger.info("Inserted product data into the product table");

        logger.info("Inserting nutrient data into the nutrient table.");
//        for (Nutrient nutrient:nutrientList) {
//            nutrientRepository.insertNutrientData(nutrient);
//        }
        logger.info("Inserted nutrient data into the nutrient table.");

//        logger.info("Inserting data into the product_nutrient table.");
//        for (List<ProductNutrient> productNutrientList:productNutrientData) {
//            for (ProductNutrient productNutrient:productNutrientList) {
//                productNutrientRepository.insertProductNutrientData(productNutrient);
//            }
//        }
//        logger.info("Inserted data into the product_nutrient table.");

        return "/data";
    }


}
