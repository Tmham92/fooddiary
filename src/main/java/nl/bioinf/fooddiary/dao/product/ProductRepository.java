package nl.bioinf.fooddiary.dao.product;

import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductMeasurement;

import java.util.List;

public interface ProductRepository {

    int insertProductData(Product product);

    List<ProductDescription> getAllProductDescriptions();

    ProductMeasurement getProductByDescription(String description);

}
