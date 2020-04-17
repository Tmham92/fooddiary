package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductMeasurement;

import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Interface that support the ProductDAO, this interface is used in the data controller to call the methods in the
 * ProductDAO. This interface supports inserting the product data into the database when called.
 */
public interface ProductRepository {

    int insertProductData(Product product);

    List<ProductDescription> getAllProductDescriptions();

    ProductMeasurement getProductByDescription(String description);

}
