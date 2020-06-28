package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.NutrientRepository;
import nl.bioinf.fooddiary.dao.ProductNutrientRepository;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * DatabaseDataService class that is part of the service layer. The service layer gives instructions to a layer containing
 * classes that have the data access object (DAO) pattern. This pattern allows to isolate the application layer with
 * the persistence layer in this case a database using an abstract API. This allows both layers to evolve separately
 * without knowing anything else about each other. There are three methods that receive data from a controller class,
 * this data is then separated and each individual object is passed on to the DAO class that insert these objects in the
 * database.
 */
@Service
public class DatabaseDataService {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private ProductNutrientRepository productNutrientRepository;

    public void insertProductData(List<Product> productList) {
        logger.info("Receiving list of product, inserting each individual product into the database.");

        for (Product product:productList) {
            productRepository.insertProductData(product);
        }

        logger.info("Inserted product data into the product table.");
    }

    public void insertNutrientData(List<Nutrient> nutrientList) {
        logger.info("Receiving nutrient list, inserting each individual nutrient in the nutrient table. ");

        for (Nutrient nutrient:nutrientList) {
            nutrientRepository.insertNutrientData(nutrient);
        }

        logger.info("Inserted nutrient data into the nutrient table.");
    }

    public void insertProductNutrientData(List<List<ProductNutrient>> productNutrientData) {
        logger.info("Receiving productNutrientList, inserting each individual productNutrient in the productNutrient table. ");

        for (List<ProductNutrient> productNutrientList:productNutrientData) {
            for (ProductNutrient productNutrient:productNutrientList) {
                productNutrientRepository.insertProductNutrientData(productNutrient);
            }
        }
        logger.info("Inserted data into the product_nutrient table.");
    }
}
