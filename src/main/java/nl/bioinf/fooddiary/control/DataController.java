package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.nutrient.NutrientRepository;
import nl.bioinf.fooddiary.dao.product.ProductRepository;
import nl.bioinf.fooddiary.dao.productnutrient.ProductNutrientRepository;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class DataController {

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

    @RequestMapping(value = "/data")
    public String insertProductData(Model model) {
        logger.info("/data url has been called, showing data for testing.");

        logger.info("Inserting product data into the product table.");
        for (Product product:productList) {
            productRepository.insertProductData(product);
        }
        logger.info("Inserted product data into the product table");

        logger.info("Inserting nutrient data into the nutrient table.");
        for (Nutrient nutrient:nutrientList) {
            nutrientRepository.insertNutrientData(nutrient);
        }
        logger.info("Inserted nutrient data into the nutrient table.");

        logger.info("Inserting data into the product_nutrient table.");
        for (List<ProductNutrient> productNutrientList:productNutrientData) {
            for (ProductNutrient productNutrient:productNutrientList) {
                productNutrientRepository.insertProductNutrientData(productNutrient);
            }
        }
        logger.info("Inserted data into the product_nutrient table.");

        return "/data";
    }
}
