package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.PrepareDataInjection;
import nl.bioinf.fooddiary.model.csvparser.NutrientCsvParser;
import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.service.DatabaseDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Controller that serves the /data/... url. In the controller there are three methods that when called retrieve data
 * from various parser classes. These methods then pass the data on to the service layer.
 */
@Controller
public class DatabaseDataController {

    // Logger object used for logging the process.
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private DatabaseDataService databaseDataService;

    @RequestMapping(value = "/data/product")
    public void retrieveProductData() {
        logger.info("/data/product url has been called, retrieving product data from ProductCsvParser class.");
        List<Product> productList = new ProductCsvParser().readCsvFile();

        logger.info("Passing list of product data on to the service layer.");
        databaseDataService.insertProductData(productList);
    }

    @RequestMapping(value = "/data/nutrient")
    public void retrieveNutrientData() {
        logger.info("/data/nutrient url has been called, retrieving nutrient data from NutrientCsvParser class.");
        List<Nutrient> nutrientList = new NutrientCsvParser().readCsvFile();

        logger.info("Passing list of nutrient data on to the service layer.");
        databaseDataService.insertNutrientData(nutrientList);
    }

    @RequestMapping(value = "/data/product-nutrient")
    public void retrieveProductNutrientData() {
        logger.info("/data/product-nutrient url has been called, retrieving productNutrient data from PrepareDataInjection class.");
        List<List<ProductNutrient>> productNutrientData = new PrepareDataInjection().startRearrangeData();

        logger.info("Passing list of productNutrient data on to the service layer.");
        databaseDataService.insertProductNutrientData(productNutrientData);
    }
}
